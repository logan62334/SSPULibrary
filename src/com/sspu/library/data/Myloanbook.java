package com.sspu.library.data;

import java.io.Serializable;

public class Myloanbook implements Serializable{
	private static final long serialVersionUID = 7651556748445999685L;
	
	//ѧ��/��ʦΨһ���
	private String rdid = "";
	//����
	private String rdname = "";
	
	private String isbn = "";
	
	private String issn = "";
	//�鼮����
	private String title = "";
	//����
	private String author = "";
	//������
	private String publisher = "";
	//�۸�
	private String price = "";
	//���׽��ʱ��
	private String loandate = "";
	//����Ӧ��ʱ��
	private String returndate = "";
	//�鼮���λ��
	private String callno = "";
	//�鼮ID
	private String bookrecno = "";
	
	public String getRdid() {
		return rdid;
	}
	public void setRdid(String rdid) {
		this.rdid = rdid;
	}
	public String getRdname() {
		return rdname;
	}
	public void setRdname(String rdname) {
		this.rdname = rdname;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLoandate() {
		return loandate;
	}
	public void setLoandate(String loandate) {
		this.loandate = loandate;
	}
	public String getReturndate() {
		return returndate;
	}
	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}
	public String getCallno() {
		return callno;
	}
	public void setCallno(String callno) {
		this.callno = callno;
	}
	public String getBookrecno() {
		return bookrecno;
	}
	public void setBookrecno(String bookrecno) {
		this.bookrecno = bookrecno;
	}
}
