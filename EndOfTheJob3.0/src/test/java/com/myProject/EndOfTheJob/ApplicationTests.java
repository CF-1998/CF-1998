package com.myProject.EndOfTheJob;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.myProject.EndOfTheJob.service.ScheduledTaskService;
import com.myProject.EndOfTheJob.service.SendEmailService;
import com.myProject.EndOfTheJob.service.booksService;

@SpringBootTest
class ApplicationTests {
	@Autowired
	private booksService booksService;
	@Autowired
	private SendEmailService sendEmailService;
	@Autowired
	private TemplateEngine templateEngine;
    @Autowired
    private ScheduledTaskService ScheduledTaskService;
	@Test
	public void sendTempateEmailTest() {
		ScheduledTaskService.getTotalMoney();
	}

}
