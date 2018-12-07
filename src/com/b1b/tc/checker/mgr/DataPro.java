package com.b1b.tc.checker.mgr;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONObject;

import com.b1b.tc.checker.utils.net.HttpUtils;

public class DataPro implements DataInterface {

	public static String host = "http://172.16.6.160:810";
	private boolean isDebug = false;;

	public String getData(String partno) {
		if ("".equals(partno) || partno == null) {
			return "{\"code\":1,\"data\":[]}";
		}
		if (isDebug) {
			host = "http://192.168.10.20:8117";
		}
		String url;
		String result = "";
		String json = "{\"code\":0,\"data\":\"未知错误\"}";
		try {
			//http://172.16.6.160:810/Digikey/Get3CCheck?partno=MPC8377EWLANA
			url = host + "/Digikey/Get3CCheck?partno=" + URLEncoder.encode(partno, "utf-8");
			result = HttpUtils.getGetResult(url, null);
			json = "{\"code\":1,\"data\":" + result + "}";
			if ("[]".equals(result)) {
				json = getErrorJson("查询不到数据");
			} else if (result != null && !result.startsWith("[")) {
				json = getErrorJson(result);
			}
		} catch (UnsupportedEncodingException e1) {
			json = getErrorJson(e1.getMessage());
			e1.printStackTrace();
		} catch (IOException e) {
			json = getErrorJson(e.getMessage());
			e.printStackTrace();
		}
		return json;
	}

	private String getErrorJson(String msg) {
		String json = "{\"code\":0,\"data\":\"" + msg + "\"}";
		return json;
	}

	/*	public String getDetail2(String id, String type) {
			//	http://172.16.6.160:810/Digikey/GetChecksDetail?id=GH3045-ND&type=3c	
			String url = host + "/Digikey/GetChecksDetail";
			String query = "id=" + URLEncoder.encode(id, "utf-8");
			query += "&type=" + URLEncoder.encode(type, "utf-8");
			String url = host + "/Digikey/GetChecksDetail?" + query;
			String result = "";
			return result;
		}
	*/
	public String getDetail(String id, String type) {
		if (isDebug) {
			host = "http://192.168.10.20:8117";
		}
		String json = "";
		try {
			String query = "id=" + URLEncoder.encode(id, "utf-8");
			query += "&type=" + URLEncoder.encode(type, "utf-8");

			String url = host + "/Digikey/GetChecksDetail?" + query;
			String result = HttpUtils.getGetResult(url, null);
			json = "{\"code\":1,\"data\":" + result + "}";
			if ("[]".equals(result)) {
				json = getErrorJson("查询不到数据");
			} else if (result != null && !result.startsWith("[")) {
				json = getErrorJson(result);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json = getErrorJson(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json = getErrorJson(e.getMessage());
		}

		return json;
	}

	public String addNewData(String dType, String mType, String PartNo, String type, String uid,
			String uname) {
		String query = "?";
		String json = "";
		try {
			/*	dType=&mType=&PartNo=&type=&uid=&uname=*/
			query += "dType=" + URLEncoder.encode(dType, "utf-8");
			query += "&mType=" + URLEncoder.encode(mType, "utf-8");
			query += "&PartNo=" + URLEncoder.encode(PartNo, "utf-8");
			query += "&type=" + URLEncoder.encode(type, "utf-8");
			query += "&uid=" + URLEncoder.encode(uid, "utf-8");
			query += "&uname=" + URLEncoder.encode(uname, "utf-8");
			String url = host + "/Digikey/InsertZDYInfo" + query;
			String result = HttpUtils.getGetResult(url, null);
			if ("保存成功".equals(result)) {
				json = "{\"code\":1,\"data\":\"" + result + "\"}";
			} else if ("[]".equals(result)) {
				json = getErrorJson("查询不到数据");
			} else if (result != null && !result.startsWith("[")) {
				json = getErrorJson(result);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	public String login(String uid, String pwd) {
		//http://172.16.6.160:810/Digikey/LoginInfo?uid=3548&pwd=123456789
		String query = "?";
		String json = "";
		try {
			query += "uid=" + URLEncoder.encode(uid, "utf-8");
			query += "&pwd=" + URLEncoder.encode(pwd, "utf-8");
			String url = host + "/Digikey/LoginInfo" + query;
			String result = HttpUtils.getGetResult(url, null);
			json = "{\"code\":1,\"data\":" + result + "}";
			if ("[]".equals(result)) {
				json = getErrorJson("查询不到数据");
			} else if (result != null && !result.startsWith("[")) {
				json = getErrorJson(result);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	String getNewDetail() {

		//http://172.16.6.160:810/Digikey/InsertZDYInfo?dType=&mType=&PartNo=&type=&uid=&uname=
		//GetSqlServerEmbargos_3C
		return null;
	}

	public String getSafeString(JSONObject obj, String tagName) {
		Object o = obj.get(tagName);
		if (o == null) {
			return "null";
		} else {
			return o.toString();
		}
	}

	public static void main(String[] args) {
		DataInterface po = new DataPro();
		//		String res = po.getData("asd");
		String res = po.getDetail("317-1143-ND", "3c");
		res += "\n";
		res += po.getDetail("74b4a8b33b0af6a088e4fcdb28a38b3f", "禁运");
		res += "\n";
		res += po.getData("sdf");
		res += "\n";
		res += po.login("101", "62105300");
		System.out.println("data:\n" + res);
		FileOutputStream fio;
		try {
			fio = new FileOutputStream("out.txt");
			fio.write(res.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
