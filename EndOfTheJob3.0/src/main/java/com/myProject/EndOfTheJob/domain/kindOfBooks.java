package com.myProject.EndOfTheJob.domain;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



//图书种类实体类
@Entity
public class kindOfBooks implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String bookKind;
	@OneToMany(
			   fetch=FetchType.LAZY,  //表示抓取策略,默认为FetchType.LAZY,因为关联的多个对象通常不必从数据库预先读取到内存 
			   targetEntity=books.class,//由One的一方指向Many的一方
			   mappedBy="kindOfBooks",//这个属性应该等于Many的一方中含有One类的属性的名称
			   cascade={CascadeType.PERSIST,CascadeType.REMOVE}//表示级联操作策略
			  )     
	private List<books> books = new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookKind() {
		return bookKind;
	}
	public void setBookKind(String bookKind) {
		this.bookKind = bookKind;
	}
	public List<books> getBooks() {
		return books;
	}
	public void setBooks(List<books> books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return "kindOfBooks [id=" + id + ", bookKind=" + bookKind + ", books=" + books + "]";
	}
	
	
	

	
}
