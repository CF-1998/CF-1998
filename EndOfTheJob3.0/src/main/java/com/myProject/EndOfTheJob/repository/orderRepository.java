package com.myProject.EndOfTheJob.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.myProject.EndOfTheJob.domain.orderWithUsers;

public interface orderRepository extends JpaRepository<orderWithUsers, Integer> {
	//分页查询
		public Page<orderWithUsers> findAll(Pageable pageable);
}
