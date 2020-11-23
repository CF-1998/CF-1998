package com.myProject.EndOfTheJob.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.EndOfTheJob.domain.books;
public interface booksRepository extends JpaRepository<books, Integer>{
	//分页查询
	public Page<books> findAll(Pageable pageable);
	
}
