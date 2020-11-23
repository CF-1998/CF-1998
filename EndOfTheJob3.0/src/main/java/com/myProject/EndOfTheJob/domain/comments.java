package com.myProject.EndOfTheJob.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 评论实体类
 */
@Entity
public class comments implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;         // 评论id
    private String content;    // 评论内容
    private Date created;      // 评论日期
    private String author;     // 评论作者名
    private String ip;          // 评论用户登录ip
    private String status;     // 评论状态，默认审核通过approved

	@JsonIgnore
	//评论与文章之间是多对一
	@ManyToOne
	//@JoinColumn 注解的作用：用来指定与所操作实体或实体集合相关联的数据库表中的列字段。
	//name属性指明Comment对应的表中的列article_id做为外键，referencedColumnName指明article对应的表中列id为主键
	@JoinColumn(name="books_id",referencedColumnName="id")
    private books books;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public books getBooks() {
		return books;
	}

	public void setBooks(books books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "comments [id=" + id + ", content=" + content + ", created=" + created + ", author=" + author + ", ip="
				+ ip + ", status=" + status + ", books=" + books + "]";
	}
	
}
