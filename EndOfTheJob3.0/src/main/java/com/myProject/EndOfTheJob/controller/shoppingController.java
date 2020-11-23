package com.myProject.EndOfTheJob.controller;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myProject.EndOfTheJob.domain.books;
import com.myProject.EndOfTheJob.domain.kindOfBooks;
import com.myProject.EndOfTheJob.service.booksService;
import com.myProject.EndOfTheJob.service.kindOfBooksService;
import com.myProject.EndOfTheJob.service.userService;

@Controller
public class shoppingController {
    @Autowired
    private booksService booksService;
    @Autowired
    private userService userService;
    @Autowired
    private kindOfBooksService kindOfBooksService;
    @RequestMapping("/goshop")
  //获取购物车
    public String goShoppingBooks(HttpServletRequest request,Model model) {
    	HttpSession session = request.getSession();
    	Map<books, Integer> cart= (Map<books, Integer>) session.getAttribute("cart");
    	if (cart == null) {
			cart  = new HashMap<books, Integer>();
		}  
    	session.setAttribute("cart",cart);
      	//获取所有图书分类
		List<kindOfBooks> kindOfBooks = kindOfBooksService.findAll();	
		model.addAttribute("kindOfBooks", kindOfBooks);
    	return "shopping";
    }
   
    @RequestMapping("/shopping")
    //像购物车中添加商品
    public String addShoppingBooks(HttpServletRequest request,Model model) {
    	int id = Integer.parseInt(request.getParameter("booksId"));
    	books books = booksService.findByBookId(id);
//		将图书现价价格改为折扣后的价格并保留两位小数
		double oldPrice = books.getBookPrice();
		double discount = books.getBookDiscount();
		double newPrice = oldPrice/10*discount;
		BigDecimal b = new BigDecimal(newPrice); 	
		books.setBookPrice(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		/* System.out.println(id); */
    	HttpSession session = request.getSession();
    	Map<books, Integer> cart= (Map<books, Integer>) session.getAttribute("cart");
    	if (cart == null) {
			cart  = new HashMap<books, Integer>();
		}  
		  Integer count = cart.put(books, 1); 
		  if(count!=null) { 
			  cart.put(books,count+1); 
		  }
		 
    	session.setAttribute("cart",cart);
    	return "shopping";
    }
    
// 根据id删除指定图书
    @PostMapping("/deleteCart")
    public String delete(HttpServletRequest req, HttpServletResponse resp) {
    	int id = Integer.parseInt(req.getParameter("id"));
    	HttpSession session = req.getSession();
		Map<books, Integer> cart = (Map<books, Integer>) session.getAttribute("cart");
		books books = new books();
		books.setId(id);
		cart.remove(books);
    	return "shopping";
    }
//    改变商品数量
    @RequestMapping("/changeCart")
        public String changeCount(HttpServletRequest request) {
    	int id = Integer.parseInt(request.getParameter("id"));
    	int count = Integer.parseInt(request.getParameter("count"));
    	System.out.println(count);
    	HttpSession session = request.getSession();
		Map<books, Integer> cart = (Map<books, Integer>) session.getAttribute("cart");
		books books = new books();
		books.setId(id);
		if (count != 0) {
			cart.put(books, count);
		} else {
			cart.remove(books);
		}
		return "shopping";
    }
}
