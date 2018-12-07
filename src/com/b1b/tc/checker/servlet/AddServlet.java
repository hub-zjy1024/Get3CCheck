package com.b1b.tc.checker.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.b1b.tc.checker.mgr.DataInterface;
import com.b1b.tc.checker.mgr.DataPro;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String partno = request.getParameter("PartNo");
		String dtype = request.getParameter("dtype");
		String mtype = request.getParameter("mtype");
		String uid = request.getParameter("uid");
		String type = request.getParameter("type");
		String uname = request.getParameter("uname");
		DataInterface di = new DataPro();
		String result = di.addNewData(dtype, mtype, partno, type, uid, uname);
		/*		query+="PartNo="+encodeUriComponent(iPartno);
				query+="&dtype="+encodeUriComponent(iDtype);
				query+="&mtype="+encodeUriComponent(imType);
				query+="&uid="+encodeUriComponent(uid);
				query+="&uname="+encodeUriComponent(uname);*/
		int code = 0;
		try {
			JSONObject obj = new JSONObject(result);
			code = obj.getInt("code");
		} catch (Exception e) {
			e.printStackTrace();
		}
	/*	if (code == 1) {
			response.getWriter().append("1");
		} else {
			response.getWriter().append("0");
		}*/
		response.getWriter().append(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
