package com.sspu.library.data;

import java.io.Serializable;

public class Events implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7651553109545999685L;
	private int ID = 0;
	private String TITLE = "";
	private String CONTENT = "";
	private String EVENTDATE = "";
	private String ADDRESS = "";
	private String COMPERE = "";
	private String EVENTKEYWORD = "";
	private String CREATEDATE = "";
	private String CREATEUSER = "";
	private String ETYPE = "";

	public String getETYPE() {
		return ETYPE;
	}
	public void setETYPE(String eTYPE) {
		ETYPE = eTYPE;
	}
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
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String content) {
		CONTENT = content;
	}
	public String getEVENTDATE() {
		return EVENTDATE;
	}
	public void setEVENTDATE(String eventdate) {
		EVENTDATE = eventdate;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String address) {
		ADDRESS = address;
	}
	public String getCOMPERE() {
		return COMPERE;
	}
	public void setCOMPERE(String compere) {
		COMPERE = compere;
	}
	public String getEVENTKEYWORD() {
		return EVENTKEYWORD;
	}
	public void setEVENTKEYWORD(String eventkeyword) {
		EVENTKEYWORD = eventkeyword;
	}
	public String getCREATEDATE() {
		return CREATEDATE;
	}
	public void setCREATEDATE(String createdate) {
		CREATEDATE = createdate;
	}
	public String getCREATEUSER() {
		return CREATEUSER;
	}
	public void setCREATEUSER(String createuser) {
		CREATEUSER = createuser;
	}
	
	
}
