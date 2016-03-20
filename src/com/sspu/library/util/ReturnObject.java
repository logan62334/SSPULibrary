package com.sspu.library.util;

import java.util.ArrayList;

import com.sspu.library.data.Book;
import com.sspu.library.data.Broadcast;
import com.sspu.library.data.Events;
import com.sspu.library.data.Myloanbook;

public class ReturnObject {
	Book book = new Book();
	Myloanbook mylb = new Myloanbook();
	
	public Object getResult(String typestring,String xml){
		if(WorkType.req_BOOK_DETAIL_QUERY.equals(typestring)){
			return this.getBook(xml);
		}else if(WorkType.req_BOOK_QUERY.equals(typestring)|| WorkType.req_New_BOOK_QUERY.equals(typestring)){
			return this.getBookList(xml);
		}else if(WorkType.req_EXPIRE_BOOK.equals(typestring) || WorkType.req_LOAN_BOOK_HISTORY.equals(typestring) || WorkType.req_MY_LOAN_BOOK.equals(typestring)){
			return this.getMyloanbookList(xml);
		}else if(WorkType.req_NEWS_DETAIL_QUERY.equals(typestring) || WorkType.req_NOTICE_DETAIL_QUERY.equals(typestring)){
			return this.getBroadcast(xml);
		}else if(WorkType.req_NEWS_QUERY.equals(typestring) || WorkType.req_NOTICE_QUERY.equals(typestring)){
			return this.getBroadcastList(xml);
		}else if(WorkType.req_SHOW_DETAIL_QUERY.equals(typestring) || WorkType.req_TRAIN_DETAIL_QUERY.equals(typestring)){
			return this.getEvents(xml);
		}else if(WorkType.req_SHOW_QUERY.equals(typestring) || WorkType.req_TRAIN_QUERY.equals(typestring) || WorkType.req_SHOW_TRAIN_QUERY.equals(typestring)){
			return this.getEventsList(xml);
		}
		
		return null;
	}
	
	private ArrayList<Book> getBookList(String xml){
		ArrayList<Book> al = new ArrayList<Book>();
		
		while(xml.indexOf("<tr>")>0){
			xml = xml.substring(xml.indexOf("<td>")+4);
			String contentString = xml.substring(0, xml.indexOf("</tr>"));
			contentString = contentString.substring(0, contentString.lastIndexOf("</td>"));
			xml = xml.substring(xml.indexOf("</tr>"));
			String[] cs = contentString.split("</td><td>");
			
			Book bk = new Book();
			bk.setAddress(cs[0]);
			bk.setAuthor(cs[1]);
			bk.setBookContent(cs[2]);
			bk.setBookpicurl(cs[3]);
			bk.setBookrecno(cs[4]);
			bk.setBooksize(cs[5]);
			bk.setCallno(cs[6]);
			bk.setClassno(cs[7]);
			bk.setCreatetime(cs[8]);
			bk.setIsbn(cs[9]);
			bk.setIssn(cs[10]);
			bk.setLanguage(cs[11]);
			bk.setPage(cs[12]);
			bk.setPrice(cs[13]);
			bk.setPubdate(cs[14]);
			bk.setPublisher(cs[15]);
			bk.setSubject(cs[16]);
			bk.setTitle(cs[17]);
			
			al.add(bk);
		}
		
		return al;
	}
	
	private Book getBook(String xml){
		Book bk = new Book();
		if(xml.indexOf("<tr>")>0){
			xml = xml.substring(xml.indexOf("<td>")+4);
			String contentString = xml.substring(0, xml.indexOf("</tr>"));
			contentString = contentString.substring(0, contentString.lastIndexOf("</td>"));
			xml = xml.substring(xml.indexOf("</tr>"));
			String[] cs = contentString.split("</td><td>");
			
			bk.setAddress(cs[0]);
			bk.setAuthor(cs[1]);
			bk.setBookContent(cs[2]);
			bk.setBookpicurl(cs[3]);
			bk.setBookrecno(cs[4]);
			bk.setBooksize(cs[5]);
			bk.setCallno(cs[6]);
			bk.setClassno(cs[7]);
			bk.setCreatetime(cs[8]);
			bk.setIsbn(cs[9]);
			bk.setIssn(cs[10]);
			bk.setLanguage(cs[11]);
			bk.setPage(cs[12]);
			bk.setPrice(cs[13]);
			bk.setPubdate(cs[14]);
			bk.setPublisher(cs[15]);
			bk.setSubject(cs[16]);
			bk.setTitle(cs[17]);
		}
		
		return bk;
	}
	
	private ArrayList<Broadcast> getBroadcastList(String xml){
		ArrayList<Broadcast> al = new ArrayList<Broadcast>();
		
		while(xml.indexOf("<tr>")>0){
			xml = xml.substring(xml.indexOf("<td>")+4);
			String contentString = xml.substring(0, xml.indexOf("</tr>"));
			contentString = contentString.substring(0, contentString.lastIndexOf("</td>"));
			xml = xml.substring(xml.indexOf("</tr>"));
			String[] cs = contentString.split("</td><td>");
			
			Broadcast bc = new Broadcast();
			bc.setBTYPE(cs[0]);
			bc.setCONTEXT(cs[1]);
			bc.setCREATEDATE(cs[2]);
			bc.setCREATEPERSON(cs[3]);
			if(cs[4].length() >0){
				bc.setID(Integer.parseInt(cs[4]));
			}
			bc.setTITLE(cs[5]);
			
			al.add(bc);
		}
		
		return al;
	}
	
	private Broadcast getBroadcast(String xml){
		Broadcast bc = new Broadcast();
		
		if(xml.indexOf("<tr>")>0){
			xml = xml.substring(xml.indexOf("<td>")+4);
			String contentString = xml.substring(0, xml.indexOf("</tr>"));
			contentString = contentString.substring(0, contentString.lastIndexOf("</td>"));
			xml = xml.substring(xml.indexOf("</tr>"));
			String[] cs = contentString.split("</td><td>");
			
			bc = new Broadcast();
			bc.setBTYPE(cs[0]);
			bc.setCONTEXT(cs[1]);
			bc.setCREATEDATE(cs[2]);
			bc.setCREATEPERSON(cs[3]);
			if(cs[4].length() >0){
				bc.setID(Integer.parseInt(cs[4]));
			}
			bc.setTITLE(cs[5]);
		}
		return bc;
	}
	
	private ArrayList<Events> getEventsList(String xml){
		ArrayList<Events> al = new ArrayList<Events>();
		System.out.println(xml);
		while(xml.indexOf("<tr>")>0){
			xml = xml.substring(xml.indexOf("<td>")+4);
			String contentString = xml.substring(0, xml.indexOf("</tr>"));
			System.out.println("contentString = "+contentString);
			contentString = contentString.substring(0, contentString.lastIndexOf("</td>"));
			xml = xml.substring(xml.indexOf("</tr>"));
			String[] cs = contentString.split("</td><td>");
			
			Events es = new Events();
			es.setADDRESS(cs[0]);
			es.setCOMPERE(cs[1]);
			es.setCONTENT(cs[2]);
			es.setCREATEDATE(cs[3]);
			es.setCREATEUSER(cs[4]);
			es.setETYPE(cs[5]);
			es.setEVENTDATE(cs[6]);
			es.setEVENTKEYWORD(cs[7]);
			if(cs[8].length() >0){
				es.setID(Integer.parseInt(cs[8]));
			}
			es.setTITLE(cs[9]);
			
			al.add(es);
		}
		
		return al;
	}
	
	private Events getEvents(String xml){
		Events es = new Events();
		
		if(xml.indexOf("<tr>")>0){
			xml = xml.substring(xml.indexOf("<td>")+4);
			String contentString = xml.substring(0, xml.indexOf("</tr>"));
			contentString = contentString.substring(0, contentString.lastIndexOf("</td>"));
			xml = xml.substring(xml.indexOf("</tr>"));
			String[] cs = contentString.split("</td><td>");
			
			es = new Events();
			es.setADDRESS(cs[0]);
			es.setCOMPERE(cs[1]);
			es.setCONTENT(cs[2]);
			es.setCREATEDATE(cs[3]);
			es.setCREATEUSER(cs[4]);
			es.setETYPE(cs[5]);
			es.setEVENTDATE(cs[6]);
			es.setEVENTKEYWORD(cs[7]);
			if(cs[8].length() >0){
				es.setID(Integer.parseInt(cs[8]));
			}
			es.setTITLE(cs[9]);
		}
		return es;
	}
	
	private ArrayList<Myloanbook> getMyloanbookList(String xml){
		ArrayList<Myloanbook> al = new ArrayList<Myloanbook>();
		
		while(xml.indexOf("<tr>")>0){
			xml = xml.substring(xml.indexOf("<td>")+4);
			String contentString = xml.substring(0, xml.indexOf("</tr>"));
			contentString = contentString.substring(0, contentString.lastIndexOf("</td>"));
			xml = xml.substring(xml.indexOf("</tr>"));
			String[] cs = contentString.split("</td><td>");
			
			Myloanbook mlb = new Myloanbook();
			mlb.setAuthor(cs[0]);
			mlb.setBookrecno(cs[1]);
			mlb.setCallno(cs[2]);
			mlb.setIsbn(cs[3]);
			mlb.setIssn(cs[4]);
			mlb.setLoandate(cs[5]);
			mlb.setPrice(cs[6]);
			mlb.setPublisher(cs[7]);
			mlb.setRdid(cs[8]);
			mlb.setRdname(cs[9]);
			mlb.setReturndate(cs[10]);
			mlb.setTitle(cs[11]);
			
			al.add(mlb);
		}
		
		return al;
	}
	
//	private Myloanbook getMyloanbook(String xml){
//		
//		return null;
//	}
	

	
	//Test
	public ArrayList getBookList(){
		ArrayList al = new ArrayList();
		
		for(int i=0;i< 10;i++){
			Book bk = initBook();
			al.add(bk);
		}
		return al;
	}
	//Test
	public Book getBookDetail(){
		return initBook();
	}
	//Test
	public Book initBook(){
		Book bk = new Book();
		
		bk.setAddress("北京市海淀区清华大学出版社");
		bk.setAuthor("黄桂金");
		bk.setBookrecno("185471");
		bk.setBooksize("16开");
		bk.setCallno("TP312JA/708");
		bk.setClassno("TP312-62");
		bk.setCreatetime("2010-10-12");
		bk.setIsbn("978-7-302-16876-8");
		bk.setIssn("");
		bk.setLanguage("汉语");
		bk.setPage("569");
		bk.setPrice("69.00");
		bk.setPubdate("2008-6");
		bk.setPublisher("清华大学出版社");
		bk.setSubject("软件开发");
		bk.setTitle("PHP+MySQL完全学习手册");
		bk.setBookpicurl("http://img1.douban.com/mpic/s3215554.jpg");
		
		return bk;
	}
	
}
