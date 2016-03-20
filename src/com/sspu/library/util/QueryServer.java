package com.sspu.library.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.sspu.library.data.VisitParameters;

public class QueryServer {

	private String url = "http://202.121.241.133:8095/SSPULibService/login.do?";
	private String loginUrl = "http://202.121.241.158:8000/cas/webindex.do?act=verification&username=";
	private BufferedReader in = null;

	public Object doQuery(String typestring, VisitParameters vp) {
		String uri = this.getIntegratedUrl(url, typestring, vp);
		Object result = null;
		if (uri != null) {
			
			String xmlString = this.doServerQuery(uri);
			ReturnObject ro = new ReturnObject();

			result = ro.getResult(typestring, xmlString);
		}
		return result;
	}

	public Object doQuery(String typestring) {
		VisitParameters vp = new VisitParameters();
		String uri = this.getIntegratedUrl(url, typestring, vp);
		Object result = null;
		if (uri != null) {
			String xmlString = this.doServerQuery(uri);

			ReturnObject ro = new ReturnObject();

			result = ro.getResult(typestring, xmlString);
		}
		return result;
	}

	public String doLogin(String userid, String password) {
		loginUrl += userid + "&password=" + password;
//		System.out.println("----------loginUrl="+loginUrl);
		HttpPost postRequest = new HttpPost(loginUrl);
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", "Tom"));
//		params.add(new BasicNameValuePair("password", "123456"));
		String temp = "";
		try {
			postRequest.setEntity(new
					UrlEncodedFormEntity(params,HTTP.UTF_8));
			HttpResponse response = new DefaultHttpClient().execute(postRequest);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				String NL = System.getProperty("line.separator");
				while((line = in.readLine())!= null){
					String line1 = new String(line.getBytes("GBK"),"GB2312"); 
					sb.append(line1 +NL);
				}
				in.close();
				temp = sb.toString();
			} else {
				System.out.println("Õ¯¬Á¡¨Ω” ß∞‹");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("----------temp="+temp+"---temp.length="+temp.length());
		if(temp.length() != 12){
			temp = null;
		}
	
		return temp;
	}
	
//	public String doLogin(String userid, String password) {
//		if (userid == "123") {
//			return null;
//		}
//
//		return this.appendZero(userid);
//	}

	private String doServerQuery(String url) {
		HttpPost postRequest = new HttpPost(url);
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", "Tom"));
		params.add(new BasicNameValuePair("password", "123456"));
		String temp = "";
		try {
			postRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = new DefaultHttpClient()
					.execute(postRequest);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				in = new BufferedReader(new InputStreamReader(response
						.getEntity().getContent()));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				String NL = System.getProperty("line.separator");
				while ((line = in.readLine()) != null) {
					String line1 = new String(line.getBytes("GBK"), "GB2312");
					sb.append(line1 + NL);
				}
				in.close();
				temp = getTrueString(sb.toString());
			} else {
				System.out.println("Õ¯¬Á¡¨Ω” ß∞‹");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	private String getIntegratedUrl(String url, String typestring,
			VisitParameters vp) {
		String reqUrl = null;
		if (WorkType.req_BOOK_DETAIL_QUERY.equals(typestring)
				&& vp.getBookrecno().length() > 0) {
			reqUrl = url + "mtype=" + WorkType.req_BOOK_DETAIL_QUERY
					+ "&bookreno=" + vp.getBookrecno();
		} else if (WorkType.req_BOOK_QUERY.equals(typestring)) {
			reqUrl = url + "mtype=" + WorkType.req_BOOK_QUERY;
			if (vp.getStartRecordNum() >= 0) {
				reqUrl += "&startnum=" + vp.getStartRecordNum();
			}
			if (vp.getEndRecordNum() >= 0) {
				reqUrl += "&endnum=" + vp.getEndRecordNum();
			}
			if (vp.getBookQueryKeywork().length() > 0) {
				reqUrl += "&keywords=" + vp.getBookQueryKeywork();
			}
			if (vp.getBookQueryType().length() > 0) {
				reqUrl += "&searchType=" + vp.getBookQueryType();
			}

		} else if (WorkType.req_New_BOOK_QUERY.equals(typestring)) {
			reqUrl = url + "mtype=" + WorkType.req_New_BOOK_QUERY;
			if (vp.getStartRecordNum() >= 0) {
				reqUrl += "&startnum=" + vp.getStartRecordNum();
			}
			if (vp.getEndRecordNum() >= 0) {
				reqUrl += "&endnum=" + vp.getEndRecordNum();
			}
		} else if (WorkType.req_EXPIRE_BOOK.equals(typestring)
				&& vp.getLoginID().length() > 0) {
			reqUrl = url + "mtype=" + WorkType.req_EXPIRE_BOOK + "&userid="
					+ appendZero(vp.getLoginID());
		} else if (WorkType.req_LOAN_BOOK_HISTORY.equals(typestring)
				&& vp.getLoginID().length() > 0) {
			reqUrl = url + "mtype=" + WorkType.req_LOAN_BOOK_HISTORY
					+ "&userid=" + appendZero(vp.getLoginID());
			if (vp.getStartRecordNum() >= 0) {
				reqUrl += "&startnum=" + vp.getStartRecordNum();
			}
			if (vp.getEndRecordNum() >= 0) {
				reqUrl += "&endnum=" + vp.getEndRecordNum();
			}
		} else if (WorkType.req_MY_LOAN_BOOK.equals(typestring)
				&& vp.getLoginID().length() > 0) {
			reqUrl = url + "mtype=" + WorkType.req_MY_LOAN_BOOK + "&userid="
					+ appendZero(vp.getLoginID());
		} else if (WorkType.req_NEWS_DETAIL_QUERY.equals(typestring)
				&& vp.getBroadcastid() > 0) {
			reqUrl = url + "mtype=" + WorkType.req_NEWS_DETAIL_QUERY
					+ "&broadcastid=" + vp.getBroadcastid();
		} else if (WorkType.req_NEWS_QUERY.equals(typestring)) {
			reqUrl = url + "mtype=" + WorkType.req_NEWS_QUERY;
			if (vp.getStartRecordNum() >= 0) {
				reqUrl += "&startnum=" + vp.getStartRecordNum();
			}
			if (vp.getEndRecordNum() >= 0) {
				reqUrl += "&endnum=" + vp.getEndRecordNum();
			}
		} else if (WorkType.req_NOTICE_DETAIL_QUERY.equals(typestring)
				&& vp.getBroadcastid() > 0) {
			reqUrl = url + "mtype=" + WorkType.req_NOTICE_DETAIL_QUERY
					+ "&broadcastid=" + vp.getBroadcastid();
		} else if (WorkType.req_NOTICE_QUERY.equals(typestring)) {
			reqUrl = url + "mtype=" + WorkType.req_NOTICE_QUERY;
			if (vp.getStartRecordNum() >= 0) {
				reqUrl += "&startnum=" + vp.getStartRecordNum();
			}
			if (vp.getEndRecordNum() >= 0) {
				reqUrl += "&endnum=" + vp.getEndRecordNum();
			}
		} else if (WorkType.req_SHOW_DETAIL_QUERY.equals(typestring)
				&& vp.getEventsid() > 0) {
			reqUrl = url + "mtype=" + WorkType.req_SHOW_DETAIL_QUERY
					+ "&eventsid=" + vp.getEventsid();
		} else if (WorkType.req_SHOW_QUERY.equals(typestring)) {
			reqUrl = url + "mtype=" + WorkType.req_SHOW_QUERY;
			if (vp.getStartRecordNum() >= 0) {
				reqUrl += "&startnum=" + vp.getStartRecordNum();
			}
			if (vp.getEndRecordNum() >= 0) {
				reqUrl += "&endnum=" + vp.getEndRecordNum();
			}
		} else if (WorkType.req_TRAIN_DETAIL_QUERY.equals(typestring)
				&& vp.getEventsid() > 0) {
			reqUrl = url + "mtype=" + WorkType.req_TRAIN_DETAIL_QUERY
					+ "&eventsid=" + vp.getEventsid();
		} else if (WorkType.req_TRAIN_QUERY.equals(typestring)) {
			reqUrl = url + "mtype=" + WorkType.req_TRAIN_QUERY;
			if (vp.getStartRecordNum() >= 0) {
				reqUrl += "&startnum=" + vp.getStartRecordNum();
			}
			if (vp.getEndRecordNum() >= 0) {
				reqUrl += "&endnum=" + vp.getEndRecordNum();
			}
		}else if (WorkType.req_SHOW_TRAIN_QUERY.equals(typestring)) {
			reqUrl = url + "mtype=" + WorkType.req_SHOW_TRAIN_QUERY;
			if (vp.getStartRecordNum() >= 0) {
				reqUrl += "&startnum=" + vp.getStartRecordNum();
			}
			if (vp.getEndRecordNum() >= 0) {
				reqUrl += "&endnum=" + vp.getEndRecordNum();
			}
		}

		System.out.println(reqUrl);
		return reqUrl;
	}

	private String getTrueString(String org) {
		int temp1 = org.indexOf("<table");
		int temp2 = org.indexOf("</table>");
		String temp = org.substring(temp1, (temp2 + 8));

		return temp;
	}

	private String appendZero(String userid) {
		while (userid.length() < 11) {
			userid = "0" + userid;
		}
		return userid;
	}
}
