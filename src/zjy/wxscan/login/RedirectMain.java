package zjy.wxscan.login;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RedirectMain
 */
@WebServlet("/RedirectMain")
public class RedirectMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RedirectMain() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		String show_url = request.getParameter("show_url");
		//http%3A%2F%2Foa.wl.net.cn%3A8399%2FLoginChecker
		String url = "http://oa.wl.net.cn:8399/LoginChecker";
		//String url="http://192.168.10.66:8080/Get3CCheck/LoginChecker";
		url += "?id=" + URLEncoder.encode(id, "utf-8");
		if (flag != null) {
			url += "&flag=" + URLEncoder.encode(flag, "utf-8");
		}
		if (show_url != null) {
			url += "&show_url=" + URLEncoder.encode(show_url, "utf-8");
		}
		response.sendRedirect(
				"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3636754f0b243b3f&redirect_uri="
						+ URLEncoder.encode(url, "utf-8")
						+ "&response_type=code&scope=snsapi_base&state=#wechat_redirect");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
