package com.myProject.EndOfTheJob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myProject.EndOfTheJob.domain.Carousel;
import com.myProject.EndOfTheJob.repository.carouselsRepository;

@Service
@Transactional
public class carouselService {
	@Autowired
	private carouselsRepository carouselsRepository;
	public List<Carousel> findAllCarousels(){
		return carouselsRepository.findAll();
	}
}
