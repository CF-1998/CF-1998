package com.myProject.EndOfTheJob.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myProject.EndOfTheJob.domain.books;
import com.myProject.EndOfTheJob.domain.comments;
import com.myProject.EndOfTheJob.responseData.BooksResponseData;
import com.myProject.EndOfTheJob.service.booksService;
import com.myProject.EndOfTheJob.service.commentsService;
import com.myProject.EndOfTheJob.utils.MyUtils;
import com.vdurmont.emoji.EmojiParser;


@Controller
@RequestMapping("/comments")
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private booksService booksService;
	/*
	 * @Autowired private CommentServiceImpl commentServiceImpl;
	 */
    @Autowired
    private commentsService commentsService;

    // 发表评论操作
    @PostMapping(value = "/publish")
    @ResponseBody
    public BooksResponseData publishComment(HttpServletRequest request,@RequestParam int id, @RequestParam String text) {
        // 去除HTML脚本
        text = MyUtils.cleanXSS(text);
        //处理emoji表情
        text = EmojiParser.parseToAliases(text);
        // 获取当前登录用户
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 封装评论信息
        comments comment = new comments();
        books books =booksService.findById(id);
        comment.setBooks(books);
        comment.setIp(request.getRemoteAddr());
        comment.setCreated(new Date());
        comment.setAuthor(user.getUsername());
        comment.setContent(text);
        comment.setStatus("approved");
        try {
           commentsService.pushComment(comment);
            logger.info("发布评论成功，对应图书id: "+id);
            return BooksResponseData.ok();
        } catch (Exception e) {
            logger.error("发布评论失败，对应图书id: "+id +";错误描述: "+e.getMessage());
            return BooksResponseData.fail();
        }
    }
}
