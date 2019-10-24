package zjy.wxscan.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b1b.tc.checker.utils.net.StreamRead;

import zjy.wxscan.login.utils.MFCMgr;

/**
 * Servlet implementation class CheckPageServlet
 */
@WebServlet("/CheckPageServlet")
public class CheckPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String flag = request.getParameter("flag");
		if ("search".equals(flag)) {
			String uid = request.getParameter("uid");
			String partno = request.getParameter("partno");
			String pid = request.getParameter("pid");
			String cj = request.getParameter("cj");
			String checkFalg = request.getParameter("isCheck");
			boolean isCheck = false;
			if (checkFalg != null) {
				isCheck = "1".equals(checkFalg) ? true : false;
			}
			MFCMgr mgr = new MFCMgr();
			String json = mgr.GetVChuKuTongZhi2(uid, partno, pid, cj, isCheck);
			response.getWriter().append(json);
		} else if ("check".equals(flag)) {
			MFCMgr mgr = new MFCMgr();
			String pid = request.getParameter("pid");
			String mxID = request.getParameter("mxID");
			String uid = request.getParameter("uid");
			String checkinfo = request.getParameter("checkinfo");
			String IsOk = request.getParameter("IsOk");
			boolean ok = "1".equals(IsOk) ? true : false;
			String checkinfo2 = StreamRead.readFrom(request.getInputStream());
			String ret = "checkinfo1=" + checkinfo + "\t inputData=" + checkinfo2;
			//mgr.UpdateMFCCheckInfo(pid, mxID, uid, checkinfo, ok);
			//response.getWriter().append(ret);
			response.getWriter().append("暂不支持get方式提交");
		}
	}

	/**
	 * 只有doPost（）中才能获取到请求体内容
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//	doGet(request, response);
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String flag = request.getParameter("flag");
		if ("check".equals(flag)) {
			MFCMgr mgr = new MFCMgr();
			String pid = request.getParameter("pid");
			String mxID = request.getParameter("mxID");
			String uid = request.getParameter("uid");
			String checkinfo = request.getParameter("checkinfo");
			String IsOk = request.getParameter("IsOk");
			boolean ok = "1".equals(IsOk) ? true : false;
			String checkinfo2 = StreamRead.readFrom(request.getInputStream());
			String ret = "checkinfo1=" + checkinfo + "\t inputData=" + checkinfo2;
			//System.out.println("bodyData="+checkinfo2);
			ret = mgr.UpdateMFCCheckInfo(pid, mxID, uid, checkinfo2, ok);
			response.getWriter().append(ret);
		} else {
			response.getWriter().append("暂不支持post方式搜索");
		}
	}

}
