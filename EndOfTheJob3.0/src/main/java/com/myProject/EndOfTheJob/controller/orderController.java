package com.myProject.EndOfTheJob.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.myProject.EndOfTheJob.domain.books;
import com.myProject.EndOfTheJob.domain.orderWithUsers;
import com.myProject.EndOfTheJob.service.SendEmailService;
import com.myProject.EndOfTheJob.service.orderService;
import com.myProject.EndOfTheJob.utils.MyUtils;

@Controller
public class orderController {
	@Autowired
	private orderService orderService;
	@Autowired
	private SendEmailService sendEmailService;
	@Autowired
	private TemplateEngine templateEngine;

	@PostMapping("/order")
	public String goOrder(HttpServletRequest request) {
		String order_id = UUID.randomUUID() + "";// 利用UUID生成订单号
		String username = request.getParameter("username");// 收货人姓名
		String email = request.getParameter("email");// 收货人邮箱
		String address = request.getParameter("address");// 收货地址
		String tel = request.getParameter("tel");// 收货人联系方式
		int shoppingKind = Integer.parseInt(request.getParameter("shoppingKind"));// 商品种类
		int shoppingNum = Integer.parseInt(request.getParameter("shoppingNum"));// 商品数量
		double price = Double.parseDouble(request.getParameter("price"));// 商品总金额
		// 将订单信息保存在数据库中
		orderWithUsers order = new orderWithUsers(order_id, username, address, email, tel, shoppingKind, shoppingNum,
				price);
		orderService.save(order);
//发送邮件
		String to = email;
		String subject = "【订单确认】";
		Context context = new Context();
		context.setVariable("username", username);
		context.setVariable("order_id", order_id);
		String emailContent = templateEngine.process("email", context);
		sendEmailService.sendTempateEmail(to, subject, emailContent);
		// 清除session缓存，（清除购物车）
		HttpSession session = request.getSession();
		Map<books, Integer> cart = (Map<books, Integer>) session.getAttribute("cart");
		session.removeAttribute("cart");

		return "redirect:/index";
	}

}
