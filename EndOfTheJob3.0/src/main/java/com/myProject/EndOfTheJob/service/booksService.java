package com.myProject.EndOfTheJob.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myProject.EndOfTheJob.domain.books;
import com.myProject.EndOfTheJob.repository.booksRepository;
import com.myProject.EndOfTheJob.utils.PageableUtils;



@Service
@Transactional
public class booksService {
	@Autowired
	private booksRepository booksRepository;
//查找全部图书方法
	public List<books> findAll(){
		return booksRepository.findAll();
	}
//按照id查找图书
	public books findByBookId(int id) {
		Optional<books> result = booksRepository.findById(id);
    	if(result.isPresent()) {
    		books books = result.get();
    		return books;
    	}
    	return null;
	}
//按图书出版时间降序排列服务
	public List<books> findByBookCreateDate() {
		List<books> browsers = booksRepository.findAll(Sort.by(Sort.Direction.DESC, "bookCreateDate"));
		return browsers;
	}

//按销售量进行景旭排列
	public List<books> findByBookSalesVolume() {
		List<books> browsers = booksRepository.findAll(Sort.by(Sort.Direction.DESC, "bookSalesVolume"));
		return browsers;
	}
//	按图书折扣升序排列
	public List<books> findByBookDiscount() {
		List<books> books = booksRepository.findAll(Sort.by(Sort.Direction.ASC, "bookDiscount"));
		return books;
	}
//后台分页查询
	public Page<books> selectBooksWithPage(Integer page, Integer size) {
		Pageable pageable = PageableUtils.basicPage(page,size);
		Page<books> list = booksRepository.findAll(pageable);
		return list;
	}
//添加图书保存方法
	public void save(books books) {
		booksRepository.save(books);
	}
//	根据id删除指定图书方法
	public void deleById(int id) {
		booksRepository.deleteById(id);
	}
//	根据id查找指定图书的方法
	public books findById(int id) {
		Optional<books>  result = booksRepository.findById(id);
    	if(result.isPresent()) {
    		books books = result.get();
    		return books;
    	}
    	return null;
	}
}
