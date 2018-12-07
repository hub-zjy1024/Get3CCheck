package com.b1b.tc.checker.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.b1b.tc.checker.mgr.DataInterface;
import com.b1b.tc.checker.mgr.DataPro;
import com.b1b.tc.checker.utils.net.StreamRead;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		DataInterface ds = new DataPro();
		String loginRes = ds.login(uid, pwd);
		String result = "0";
		try {
			JSONObject obj = new JSONObject(loginRes);
			if (obj.getInt("code") == 1) {
				result = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().append(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String body = StreamRead.readFrom(request.getInputStream());
		String uid = "";
		String pwd = "";
		String[] split = body.split("&");
		for (String s : split) {
			String[] data = s.split("=");
			String pname = data[0];
			if (pname.equals("uid")) {
				if (data.length == 2) {
					uid = data[1];
				}
			} else if (pname.equals("pwd")) {
				if (data.length == 2) {
					pwd = data[1];
				}
			}
		}
		System.out.println("uname:" + uid);
		System.out.println("pwd:" + pwd);
		DataInterface ds = new DataPro();
		String loginRes = ds.login(uid, pwd);
		String result = "0";
		try {
			JSONObject obj = new JSONObject(loginRes);
			if (obj.getInt("code") == 1) {
				JSONObject detailObj = obj.getJSONArray("data").getJSONObject(0);
				Cookie mCk = new Cookie("uid", uid);
				int maxAge = 30 * 24 * 60 * 60;
				mCk.setMaxAge(maxAge);
				Cookie mCk2 = new Cookie("uname", detailObj.getString("Name"));
				mCk2.setMaxAge(maxAge);
				response.addCookie(mCk);
				response.addCookie(mCk2);
				result = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().append(result);
		/*doGet(request, response);*/
	}
/*	URLEncoder.encode(, "utf-8")*/
}
