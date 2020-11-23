package com.myProject.EndOfTheJob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.EndOfTheJob.domain.kindOfBooks;
import com.myProject.EndOfTheJob.repository.kindOfBooksRepository;

@Service
public class kindOfBooksService {
@Autowired
private kindOfBooksRepository kindOfBooksRepository;
public List<kindOfBooks> findAll(){
	return kindOfBooksRepository.findAll();
}
}
