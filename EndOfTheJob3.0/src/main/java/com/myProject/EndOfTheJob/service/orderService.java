package com.myProject.EndOfTheJob.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.myProject.EndOfTheJob.domain.orderWithUsers;
import com.myProject.EndOfTheJob.repository.orderRepository;
import com.myProject.EndOfTheJob.utils.PageableUtils;

@Transactional
@Service
public class orderService {
@Autowired
private orderRepository orderRepository;

//后台分页查询
	public Page<orderWithUsers> selectOrderWithPage(Integer page, Integer size) {
		Pageable pageable = PageableUtils.basicPage(page,size);
		Page<orderWithUsers> list = orderRepository.findAll(pageable);
		return list;
	}
//添加订单保存方法
	public void save(orderWithUsers orderWithUsers) {
		orderRepository.save(orderWithUsers);
	}
//	根据id删除指定订单方法
	public void deleById(int id) {
		orderRepository.deleteById(id);
	}
//	根据id查找指定定订单的方法
	public orderWithUsers findById(int id) {
		Optional<orderWithUsers>  result = orderRepository.findById(id);
  	if(result.isPresent()) {
  		orderWithUsers orderWithUsers = result.get();
  		return orderWithUsers;
  	}
  	return null;
	}
//得到所有订单信息方法
	public List<orderWithUsers> findAll(){
		return orderRepository.findAll();

	}
}
