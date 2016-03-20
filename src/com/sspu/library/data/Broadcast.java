package com.sspu.library.data;

import java.io.Serializable;

public class Broadcast implements Serializable {
	private static final long serialVersionUID = 7651553109545999685L;
	private int ID = 0;
	private String TITLE = "";
	private String CONTEXT = "";
	private String BTYPE = "";
	private String CREATEDATE = "";
	private String CREATEPERSON = "";
	
	
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String title) {
		TITLE = title;
	}
	public String getCONTEXT() {
		return CONTEXT;
	}
	public void setCONTEXT(String context) {
		CONTEXT = context;
	}
	public String getBTYPE() {
		return BTYPE;
	}
	public void setBTYPE(String btype) {
		BTYPE = btype;
	}
	public String getCREATEDATE() {
		return CREATEDATE;
	}
	public void setCREATEDATE(String createdate) {
		CREATEDATE = createdate;
	}
	public String getCREATEPERSON() {
		return CREATEPERSON;
	}
	public void setCREATEPERSON(String createperson) {
		CREATEPERSON = createperson;
	}
	
	
}
