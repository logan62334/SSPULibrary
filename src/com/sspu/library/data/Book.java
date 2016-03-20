package com.sspu.library.data;

import java.io.Serializable;

public class Book implements Serializable {
	private static final long serialVersionUID = -7060210544600464481L;
	// 图书馆书籍唯一标示
	private String bookrecno = "";
	// 书籍信息录入时间
	private String createtime = "";
	// 未知
	private String classno = "";
	// 出版社地址
	private String address = "";
	// 作者
	private String author = "";
	// 出版日期
	private String pubdate = "";
	private String isbn = "";
	private String issn = "";
	// 语言
	private String language = "";
	// 页数
	private String page = "";
	// 定价
	private String price = "";
	// 出版商
	private String publisher = "";
	// 书本尺寸
	private String booksize = "";
	// 标题
	private String title = "";
	// 分类栏目
	private String subject = "";
	// 图书馆内书籍位置
	private String callno = "";
	// 书籍图片
	private String bookpicurl = "";
	// 书籍介绍
	private String bookContent = "";

	public String getBookContent() {
		return bookContent;
	}

	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}

	public String getBookpicurl() {
		return bookpicurl;
	}

	public void setBookpicurl(String bookpicurl) {
		this.bookpicurl = bookpicurl;
	}

	public String getBookrecno() {
		return bookrecno;
	}

	public void setBookrecno(String bookrecno) {
		this.bookrecno = bookrecno;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getClassno() {
		return classno;
	}

	public void setClassno(String classno) {
		this.classno = classno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getBooksize() {
		return booksize;
	}

	public void setBooksize(String booksize) {
		this.booksize = booksize;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCallno() {
		return callno;
	}

	public void setCallno(String callno) {
		this.callno = callno;
	}
}
