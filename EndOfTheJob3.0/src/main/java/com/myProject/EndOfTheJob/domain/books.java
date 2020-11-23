package com.myProject.EndOfTheJob.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


//图书实体类
@Entity
public class books implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String bookName;//书名
	private String bookAuthor;//作者
	private String bookDetails;//简介详细
	private String bookCover;//封面
	private String bookPlaybill;//海报
	private Date bookCreateDate;//出版时间
	private String bookPublisher;//出版社
	private double bookPrice;//价格
	private String bookSalesVolume;//销售量
	private double bookDiscount;//折扣
	private String bookTitle;//标题
	
	@JsonIgnore
	//图书与图书类型之间是多对一
	@ManyToOne
	//@JoinColumn 注解的作用：用来指定与所操作实体或实体集合相关联的数据库表中的列字段。
	//name属性指明Comment对应的表中的列kindOfBooks_id做为外键，referencedColumnName指明kindOfBooks对应的表中列id为主键
	@JoinColumn(name="kindOfBooks_id",referencedColumnName="id")
	private kindOfBooks kindOfBooks;
	
	public books() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@OneToMany(
			   fetch=FetchType.LAZY,  //表示抓取策略,默认为FetchType.LAZY,因为关联的多个对象通常不必从数据库预先读取到内存 
			   targetEntity=comments.class,//由One的一方指向Many的一方
			   mappedBy="books",//这个属性应该等于Many的一方中含有One类的属性的名称
			   cascade={CascadeType.PERSIST,CascadeType.REMOVE}//表示级联操作策略
			  ) 
	private List<comments> comments=new ArrayList<comments>();
	



	public books(String bookName, String bookAuthor, String bookDetails, String bookCover, String bookPlaybill,
			Date bookCreateDate, String bookPublisher, double bookPrice, String bookSalesVolume, double bookDiscount,
			String bookTitle, com.myProject.EndOfTheJob.domain.kindOfBooks kindOfBooks) {
		super();
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookDetails = bookDetails;
		this.bookCover = bookCover;
		this.bookPlaybill = bookPlaybill;
		this.bookCreateDate = bookCreateDate;
		this.bookPublisher = bookPublisher;
		this.bookPrice = bookPrice;
		this.bookSalesVolume = bookSalesVolume;
		this.bookDiscount = bookDiscount;
		this.bookTitle = bookTitle;
		this.kindOfBooks = kindOfBooks;
	}


	public kindOfBooks getKindOfBooks() {
		return kindOfBooks;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public void setKindOfBooks(kindOfBooks kindOfBooks) {
		this.kindOfBooks = kindOfBooks;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookDetails() {
		return bookDetails;
	}
	public void setBookDetails(String bookDetails) {
		this.bookDetails = bookDetails;
	}
	public String getBookCover() {
		return bookCover;
	}
	public void setBookCover(String bookCover) {
		this.bookCover = bookCover;
	}
	public String getBookPlaybill() {
		return bookPlaybill;
	}
	public void setBookPlaybill(String bookPlaybill) {
		this.bookPlaybill = bookPlaybill;
	}
	public Date getBookCreateDate() {
		return bookCreateDate;
	}
	public void setBookCreateDate(Date bookCreateDate) {
		this.bookCreateDate = bookCreateDate;
	}
	public String getBookPublisher() {
		return bookPublisher;
	}
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getBookSalesVolume() {
		return bookSalesVolume;
	}
	public void setBookSalesVolume(String bookSalesVolume) {
		this.bookSalesVolume = bookSalesVolume;
	}
	public double getBookDiscount() {
		return bookDiscount;
	}
	public void setBookDiscount(double bookDiscount) {
		this.bookDiscount = bookDiscount;
	}
//	评论集合
	public List<comments> getComments() {
		return comments;
	}
	public void setComments(List<comments> comments) {
		this.comments = comments;
	}
	/*
	 * @Override public String toString() { return "books [id=" + id + ", bookName="
	 * + bookName + ", bookAuthor=" + bookAuthor + ", bookDetails=" + bookDetails +
	 * ", bookCover=" + bookCover + ", bookPlaybill=" + bookPlaybill +
	 * ", bookCreateDate=" + bookCreateDate + ", bookPublisher=" + bookPublisher +
	 * ", bookPrice=" + bookPrice + ", bookSalesVolume=" + bookSalesVolume +
	 * ", bookDiscount=" + bookDiscount + ", kindOfBooks=" + kindOfBooks + "]"; }
	 */

//重写hascode和equals方法
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		books other = (books) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
}
