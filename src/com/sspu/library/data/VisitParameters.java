package com.sspu.library.data;

import java.io.Serializable;

public class VisitParameters implements Serializable{
	private static final long serialVersionUID = 5060210544600464481L;
	//通用
	private int StartRecordNum= 0;
	private int EndRecordNum= 0;
	private String Rdid = "";
	//登录
	private String LoginID= "";
	private String LoginPassword ="";
	//书本查询参数
	private String bookQueryType ="";
	private String bookQueryKeywork = "";
	//书本详细
	private String bookrecno ="";
	//展览详细
	private int eventsid = 0;
	//公告\新闻详细
	private int broadcastid = 0;

	public String getBookQueryType() {
		return bookQueryType;
	}
	public void setBookQueryType(String bookQueryType) {
		this.bookQueryType = bookQueryType;
	}
	public String getBookQueryKeywork() {
		return bookQueryKeywork;
	}
	public void setBookQueryKeywork(String bookQueryKeywork) {
		this.bookQueryKeywork = bookQueryKeywork;
	}
	public int getStartRecordNum() {
		return StartRecordNum;
	}
	public void setStartRecordNum(int startRecordNum) {
		StartRecordNum = startRecordNum;
	}
	public int getEndRecordNum() {
		return EndRecordNum;
	}
	public void setEndRecordNum(int endRecordNum) {
		EndRecordNum = endRecordNum;
	}
	public String getRdid() {
		return Rdid;
	}
	public void setRdid(String rdid) {
		Rdid = rdid;
	}
	public String getLoginID() {
		return LoginID;
	}
	public void setLoginID(String loginID) {
		LoginID = loginID;
	}
	public String getLoginPassword() {
		return LoginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		LoginPassword = loginPassword;
	}
	public String getBookrecno() {
		return bookrecno;
	}
	public void setBookrecno(String bookrecno) {
		this.bookrecno = bookrecno;
	}
	public int getEventsid() {
		return eventsid;
	}
	public void setEventsid(int eventsid) {
		this.eventsid = eventsid;
	}
	public int getBroadcastid() {
		return broadcastid;
	}
	public void setBroadcastid(int broadcastid) {
		this.broadcastid = broadcastid;
	}
}
