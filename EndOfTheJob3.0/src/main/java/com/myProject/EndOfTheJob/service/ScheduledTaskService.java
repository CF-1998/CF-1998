package com.myProject.EndOfTheJob.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.myProject.EndOfTheJob.domain.orderWithUsers;

@Service
@Component
public class ScheduledTaskService {
	@Autowired
	private orderService orderService;
	@Autowired
	private SendEmailService sendEmailService;
	@Autowired
	private TemplateEngine templateEngine;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM--dd HH:mm:ss");
	//每月1日12点发送网站至今总收益邮件
	@Scheduled(cron = "0 0 12 1 * ?")
	public void getTotalMoney() {
		List<orderWithUsers> orderWithUsers = orderService.findAll();
		double totalMoney = 0 ;
		for (orderWithUsers order: orderWithUsers) {
			double price =  order.getPrice();
			totalMoney +=price;
		}
		String to = "672235206@qq.com";
		String subject = "【收益统计】";
		Context context = new Context();
		context.setVariable("totalMoney", totalMoney);
		String emailContent = templateEngine.process("/admin/SendTotalMoney", context);
		sendEmailService.sendTempateEmail(to, subject, emailContent);

	}
	
	
}
