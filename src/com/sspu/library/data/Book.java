package com.sspu.library.data;

import java.io.Serializable;

public class Book implements Serializable {
	private static final long serialVersionUID = -7060210544600464481L;
	// ͼ����鼮Ψһ��ʾ
	private String bookrecno = "";
	// �鼮��Ϣ¼��ʱ��
	private String createtime = "";
	// δ֪
	private String classno = "";
	// �������ַ
	private String address = "";
	// ����
	private String author = "";
	// ��������
	private String pubdate = "";
	private String isbn = "";
	private String issn = "";
	// ����
	private String language = "";
	// ҳ��
	private String page = "";
	// ����
	private String price = "";
	// ������
	private String publisher = "";
	// �鱾�ߴ�
	private String booksize = "";
	// ����
	private String title = "";
	// ������Ŀ
	private String subject = "";
	// ͼ������鼮λ��
	private String callno = "";
	// �鼮ͼƬ
	private String bookpicurl = "";
	// �鼮����
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
