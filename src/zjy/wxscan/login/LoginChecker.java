package zjy.wxscan.login;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.b1b.tc.checker.utils.net.HttpUtils;

import zjy.wxscan.login.utils.ContentUrl;

/**
 * Servlet implementation class LoginChecker
 */
@WebServlet("/LoginChecker")
public class LoginChecker extends HttpServlet {
	private static final Logger mLogger = Logger.getLogger(LoginChecker.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginChecker() {
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
		String code = request.getParameter("code");
		String corpId = "wx3636754f0b243b3f";
		//String corpsecret="ckMm-KDPHLVdosqhKu8IF1aLE8eh-zm3dh9EPp_C230";
		String corpsecret = "wJmyzAjXTliLbbxTv9bUJWiQ9tDIw20V8b2HIDmMBr31EugIYqtyawk7aAcgeLWB";
		String token = WxTokenHelper.getToken(corpId, corpsecret);
		String uid = getUserInfo(code, token);
		uid = "101";
		if (uid == null) {//http://172.16.6.61:8399/
			response.sendRedirect(request.getContextPath() + "/login.html");
		} else {
			Cookie cId = new Cookie("wxid", uid);
			cId.setMaxAge(30 *24* 60 * 60);
			response.addCookie(cId);
			response.sendRedirect(request.getContextPath() + "/MfcSearch.jsp");
			//			response.sendRedirect("http://oa.wl.net.cn:8399/MfcSearch.jsp");
			//response.getWriter().append("当前用户ID为：" + uid);
			//	response.sendRedirect("http://www.baidu.com");
			//response.sendRedirect("http://www.baidu.com");
		}
	}

	public String getUserInfo(String code, String token) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?";
		url += "access_token=" + token;
		url += "&code=" + code;
		try {
			String result = HttpUtils.getGetResult(url, null);
			mLogger.warning("getUserRes=" + result);
			// "UserId":"USERID",
			JSONObject obj = new JSONObject(result);
			if (obj.has("UserId")) {
				return obj.getString("UserId");
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
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
