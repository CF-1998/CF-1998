package com.myProject.EndOfTheJob.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Carousel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String carouselImg;
	private String carouselImgControl;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCarouselImg() {
		return carouselImg;
	}
	public void setCarouselImg(String carouselImg) {
		this.carouselImg = carouselImg;
	}
	public String getCarouselImgControl() {
		return carouselImgControl;
	}
	public void setCarouselImgControl(String carouselImgControl) {
		this.carouselImgControl = carouselImgControl;
	}
}
