package com.myProject.EndOfTheJob.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.myProject.EndOfTheJob.domain.books;
import com.myProject.EndOfTheJob.domain.comments;
import com.myProject.EndOfTheJob.domain.kindOfBooks;
import com.myProject.EndOfTheJob.domain.orderWithUsers;
import com.myProject.EndOfTheJob.service.booksService;
import com.myProject.EndOfTheJob.service.commentsService;
import com.myProject.EndOfTheJob.service.kindOfBooksService;
import com.myProject.EndOfTheJob.service.orderService;




@Controller
@RequestMapping("/admin")
public class adminController {
	@Autowired
	private booksService booksService;
	@Autowired
	private kindOfBooksService kindOfBooksService;
	@Autowired
	private commentsService commentsService;
	@Autowired
	private orderService orderService;
	// 跳转到后台图书分页列表

	@RequestMapping(value = { "", "/booksList/{page}", "/booksList" })
	public String index(@PathVariable(value = "page", required = false) String page,
			@RequestParam(value = "size", defaultValue = "5") int size, HttpServletRequest request) {
		if (page == null) {
			page = "0";
		}
		int pageIndex = Integer.parseInt(page);
		Page<books> booksList = booksService.selectBooksWithPage(pageIndex, size);
		request.setAttribute("booksList", booksList);
		return "admin/books_list";
	}

	//跳转到添加页面
			@GetMapping("/addbooks")
			public String addBooks(Model model) {
				model.addAttribute("books", new books());
				List<kindOfBooks> kindOfBook = kindOfBooksService.findAll();	
				model.addAttribute("kindOfBook", kindOfBook);
				return "admin/add_books";
			}
   
	// 文件上传管理
			@ResponseBody
			@PostMapping("/uploadFile")
			public String uploadFile(@RequestParam("file") MultipartFile[] fileUpload, Model model)  {
				// 默认文件上传成功，并返回状态信息
				Boolean success = true;
				String fileNamelist = "";// 拼接文件名
				for (MultipartFile file : fileUpload) {
					// 获取文件名以及后缀名
					String fileName = file.getOriginalFilename();
					// 重新生成文件名（根据具体情况生成对应文件名）
					fileName = UUID.randomUUID() + "_" + fileName;
					// 文件路径
					String dirPath = "";
					//获取当前系统
					String os = System.getProperty("os.name");
					if (os.toLowerCase().startsWith("win")) {
						dirPath = "e:" + File.separator + "2020xxxy实训" + File.separator + "workspace" + File.separator + "EndOfTheJob3.0" + File.separator +"src" + File.separator +"main" + File.separator +"resources" + File.separator +"static" + File.separator +"img" + File.separator ;
					} else {
						dirPath = "/webapps/img/";
					}
					System.out.println(dirPath);
					File filePath = new File(dirPath);
					// 指定上传文件本地存储目录，不存在需要提前创建
					if (!filePath.exists()) {
						filePath.mkdirs();
					}
					try {
						fileNamelist = fileNamelist + fileName + "#";
						file.transferTo(new File(dirPath + fileName));
					} catch (Exception e) {
						e.printStackTrace();
						success = false;
						// 上传失败，返回失败信息
					}
				}
				if (success) {
					String result = fileNamelist.substring(0, fileNamelist.length() - 1);
					System.out.println(result);
					return JSON.toJSONString(result);
					
				} else {
					return JSON.toJSONString("上传失败！");
				}
			}
			
			@RequestMapping("/add_books")
			public String add(books books) {
				
				booksService.save(books);
				return "redirect:/admin/booksList";
			}
//			 跳转到id对应的编辑页面
			@RequestMapping("/edit/{id}")
			public String booksUpdate(Model model, @PathVariable int id) {
				books books = booksService.findById(id);
				model.addAttribute("books", books);
				List<kindOfBooks> kindOfBook = kindOfBooksService.findAll();	
				model.addAttribute("kindOfBook", kindOfBook);
				return "admin/books_edit";
			}
			
			@RequestMapping("/edit")
			public String update(books books, int id) {
				booksService.save(books);
				return "redirect:/admin/booksList";
			}
//根据id删除指定图书信息
			@RequestMapping("/delete/{id}")
			public String delete(@PathVariable int id) {
				booksService.deleById(id);
				return "redirect:/admin/booksList";
			}
//跳转到评论管理
			@RequestMapping(value = {"/comments/{page}", "/comments" })
			public String comments(@PathVariable(value = "page", required = false) String page,
					@RequestParam(value = "size", defaultValue = "5") int size, HttpServletRequest request) {
				if (page == null) {
					page = "0";
				}
				int pageIndex = Integer.parseInt(page);
				Page<comments> comments = commentsService.selectCommentsWithPage(pageIndex, size);
				request.setAttribute("comments", comments);
				return "admin/book_comments";
			}
//根据id删除指定图书评论信息
			@RequestMapping("/comments_del/{id}")
			public String delete_comments(@PathVariable int id) {
				commentsService.deleById(id);
				return "redirect:/admin/comments";
			}
//跳转到订单管理页面
			@RequestMapping(value = {"/orders/{page}", "/orders" })
			public String orders(@PathVariable(value = "page", required = false) String page,
					@RequestParam(value = "size", defaultValue = "5") int size, HttpServletRequest request) {
				if (page == null) {
					page = "0";
				}
				int pageIndex = Integer.parseInt(page);
				Page<orderWithUsers> orders = orderService.selectOrderWithPage(pageIndex, size);
				request.setAttribute("orders", orders);
				return "admin/order";
			}
//根据id删除指定订单信息
			@RequestMapping("/delateOrder/{id}")
			public String delete_orders(@PathVariable int id) {
				orderService.deleById(id);
				return "redirect:/admin/orders";
			}
//发货功能未实现
}
