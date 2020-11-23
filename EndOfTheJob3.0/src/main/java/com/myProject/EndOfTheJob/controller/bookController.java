package com.myProject.EndOfTheJob.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.myProject.EndOfTheJob.domain.Carousel;
import com.myProject.EndOfTheJob.domain.Users;
import com.myProject.EndOfTheJob.domain.books;
import com.myProject.EndOfTheJob.domain.kindOfBooks;
import com.myProject.EndOfTheJob.domain.user_authority;
import com.myProject.EndOfTheJob.service.booksService;
import com.myProject.EndOfTheJob.service.carouselService;
import com.myProject.EndOfTheJob.service.kindOfBooksService;
import com.myProject.EndOfTheJob.service.userService;

@Controller
public class bookController {
	@Autowired
	private userService userService;
	@Autowired
	private carouselService carouselService;
	@Autowired
	private booksService booksService;
	@Autowired
	private kindOfBooksService kindOfBooksService;
//	获取到首页
	@GetMapping("/index")
	public String toIndex(Model model,HttpServletRequest request) {
		List<Carousel> carousels = carouselService.findAllCarousels();
		model.addAttribute("carousels", carousels);
//       按时间降序排列
		List<books> booksCreateDate = booksService.findByBookCreateDate();
		List<books> bookCreateDate = new ArrayList<books>();
		for (int i = 0; i < 6; i++) {
			bookCreateDate.add(booksCreateDate.get(i));
		}
		model.addAttribute("booksOfBookCreateDate", bookCreateDate);
//      按销售量降序排列
		List<books> booksSalesVolume = booksService.findByBookSalesVolume();
		List<books> bookSalesVolume = new ArrayList<books>();
		for (int i = 0; i < 6; i++) {
			bookSalesVolume.add(booksSalesVolume.get(i));
		}
		model.addAttribute("booksOfBookSalesVolume", bookSalesVolume);
//	      按折扣降序排列
		List<books> booksDiscount = booksService.findByBookDiscount();
		List<books> bookDiscount = new ArrayList<books>();
		for (int i = 0; i < 8; i++) {
			bookDiscount.add(booksDiscount.get(i));
		}
		model.addAttribute("booksOfBookDiscount", bookDiscount);
//获取所有图书分类
		List<kindOfBooks> kindOfBooks = kindOfBooksService.findAll();	
		model.addAttribute("kindOfBooks", kindOfBooks);

		return "index";
	}
	// 向登录页面跳转，同时封装原始页面地址
	@GetMapping("/login")
	public String login(HttpServletRequest request, Map map) {
		// 分别获取请求头和参数url中的原始访问路径
		String referer = request.getHeader("Referer");
		String url = request.getParameter("url");
		System.out.println("referer= " + referer);
		System.out.println("url= " + url);

		// 如果参数url中已经封装了原始页面路径，直接返回该路径
		if (url != null && !url.equals("")) {
			map.put("url", url);
			// 如果请求头本身包含登录，将重定向url设为空，让后台通过用户角色进行选择跳转
		} else if (referer != null && referer.contains("/login")) {
			map.put("url", "");
		} else {
			// 否则的话，就记住请求头中的原始访问路径
			map.put("url", referer);
		}
		return "login";
	}

	// 对Security拦截的无权限访问异常处理路径映射
	@GetMapping(value = "/errorPage/{page}")
	public String AccessExecptionHandler(@PathVariable("page") String page) {
		return page;
	}

	// 注册新用户
	@GetMapping("/register")
	public String toRegister(Model model) {
		model.addAttribute("users", new Users());
		List<kindOfBooks> kindOfBooks = kindOfBooksService.findAll();	
		model.addAttribute("kindOfBooks", kindOfBooks);
		return "register";
	}

	@RequestMapping("/addusers")
	public String add(Users user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setValid(true);
		userService.save(user);
		Users users = userService.findByName(user.getUserName());
		userService.save(new user_authority(users.getId(), 2));
		return "login";
	}

	@PostMapping("/checkRepeatName")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置响应与请求编码问题
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String username = req.getParameter("username");
		Users users = userService.findByName(username);
		System.out.println(1);
		if (users != null) {
			resp.getWriter().print("1");
		} else {
			resp.getWriter().print("0");
		}
	}

//	跳转图书详情页面
	@GetMapping("/details/{id}")
	public String getDetailsPage(@PathVariable("id") int id, Model model) {
		books book = booksService.findByBookId(id);
//		将图书现价价格改为折扣后的价格并保留两位小数
		double oldPrice = book.getBookPrice();
		double discount = book.getBookDiscount();
		double newPrice = oldPrice/10*discount;
		BigDecimal b = new BigDecimal(newPrice); 	
		book.setBookPrice(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		// List<books> books = booksService.findAll();
		model.addAttribute("book", book);
		List<kindOfBooks> kindOfBooks = kindOfBooksService.findAll();	
		model.addAttribute("kindOfBooks", kindOfBooks);
		// model.addAttribute("books", books);
		return "book_details";
	}

}
