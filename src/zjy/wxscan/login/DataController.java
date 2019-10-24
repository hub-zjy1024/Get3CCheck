package zjy.wxscan.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zjy.wxscan.login.utils.MFCMgr;

/**
 * Servlet implementation class DataControler
 */
@WebServlet("/DataController")
public class DataController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String id;
		String checkID;
		String checkTime;
		id = request.getParameter("id");
		checkID = request.getParameter("checkID");
		checkTime = request.getParameter("checkTime");
		MFCMgr mgr = new MFCMgr();
		String res = mgr.SetMFCCheckInfo(id, checkID, checkTime);
		if ("保存成功".equals(res)) {
			response.getWriter().append("1");
		} else {
			response.getWriter().append("0");
		}
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
