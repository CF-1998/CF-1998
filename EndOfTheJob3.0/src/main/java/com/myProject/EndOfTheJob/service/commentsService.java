package com.myProject.EndOfTheJob.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.myProject.EndOfTheJob.domain.comments;
import com.myProject.EndOfTheJob.repository.commentsRepository;
import com.myProject.EndOfTheJob.utils.PageableUtils;
@Service
@Transactional
public class commentsService {
@Autowired
private commentsRepository commentsRepository;


public Page<comments> getComments(Integer id, int page, int size) {
	Pageable pageable=PageableUtils.basicPage(page, size, "id");
	Page<comments> commentList = commentsRepository.findByBooksId(id, pageable);
	return commentList;
}


public void pushComment(comments comments) {
	comments = commentsRepository.save(comments);
	//books books = comments.getBooks();
	//Statistic statistic = movies.getStatistic();
	//statistic.setCommentsNum(statistic.getCommentsNum() + 1);
	//statisticRepository.save(statistic);

}
public List<comments> findAll(){
	return commentsRepository.findAll();
}
//后台分页查询
//后台分页查询
	public Page<comments> selectCommentsWithPage(Integer page, Integer size) {
		Pageable pageable = PageableUtils.basicPage(page,size);
		Page<comments> list = commentsRepository.findAll(pageable);
		return list;
	}
//根据id删除指定图书评论
public void deleById(int id) {
	commentsRepository.deleteById(id);
}
}
