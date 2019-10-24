package zjy.wxscan.login;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import zjy.wxscan.login.bussiness.LoginMgr;

/**
 * Servlet implementation class LoginChecker
 */
@WebServlet("/LoginChecker")
public class LoginChecker extends HttpServlet {
	//	private static final Logger mLogger = Logger.getLogger(LoginChecker.class.getName());
	private static final org.slf4j.Logger mLogger = LoggerFactory.getLogger(LoginChecker.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginChecker() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String code = request.getParameter("code");
		String tag = request.getParameter("tag");
		String show_url = request.getParameter("show_url");
		LoginMgr mLMgr = new LoginMgr();
		if ("confirm".equals(tag)) {
			int erCode = 0;
			String msg = "登录失败";
			String uid = request.getParameter("uid");
			String qrId = request.getParameter("qrId");
			String fromFlag = request.getParameter("flag");
			try {
				String updateLogin = mLMgr.changeQrLoginState(uid, qrId, "已登录");
				//			long nowTime=System.currentTimeMillis();
				//			int i=(int) (nowTime%2);
				//			String updateLogin="";
				//			if(i==0){
				//				updateLogin="ok";
				//			}
				if ("ok".equals(updateLogin)) {
					erCode = 1;
					msg = "授权'" + fromFlag + "'登录成功,登录id=" + uid;
					//				response.sendRedirect(request.getContextPath() + "/Error.jsp?errorCode="
					//						+ erCode + "&msg=" + URLEncoder.encode(msg, "utf-8"));
					/*response.sendRedirect(request.getContextPath() + "/DyjConfirm.jsp?uid=" + erCode
							+ "&qrId=" + URLEncoder.encode(msg, "utf-8") + "&flag="
							+ URLEncoder.encode(fromFlag, "utf-8"));
					return;*/
				} else {
					msg = "登录失败,结果=" + updateLogin;
				}
			} catch (Exception e) {
				msg = "登录失败，" + e.getMessage();
			}
			response.sendRedirect(request.getContextPath() + "/Error.jsp?errorCode=" + erCode
					+ "&msg=" + URLEncoder.encode(msg, "utf-8") + "&flag="
					+ URLEncoder.encode(fromFlag, "utf-8"));
		} else {
			String qrId = request.getParameter("id");
			String fromFlag = request.getParameter("flag");
			StringBuilder sblog = new StringBuilder();

			Cookie[] cookies = request.getCookies();

			if (fromFlag == null) {
				fromFlag = "pc大赢家";
			}
			int erCode = 0;
			String msg = "登录失败";
			if (qrId == null) {
				msg = "非法访问，缺少参数";
			}
			String uid;
			try {
				uid = mLMgr.getUserInfoFromWx(code);

				String cacheCodeKey = "cCode";
				String cacheCode = "";
				if (cookies != null) {
					for (Cookie tC : cookies) {
						String name = tC.getName();
						if (name.equals(cacheCodeKey)) {
							cacheCode = tC.getValue();
						}
					}
				}
				if ("".equals(cacheCode)) {
					cacheCode = code;
					response.addCookie(new Cookie(cacheCodeKey, cacheCode));
				} else {
					throw new IOException("请当前条码已使用，请刷新");
				}
				try {
					String res = mLMgr.GetUserInfoByAllInfoByID(uid);
					JSONObject obj = new JSONObject(res);
					JSONArray jarray = obj.getJSONArray("表");
					JSONObject tempobj = jarray.getJSONObject(0);
					String isKilled = tempobj.getString("killed");
					if ("1".equals(isKilled)) {
						throw new Exception(uid + " 员工已经离职");
					}

					mLMgr.changeQrLoginState(uid, qrId, "等待登录");
					String redirectUrl = request.getContextPath() + "/DyjConfirm.jsp?uid=" + uid
							+ "&qrId=" + URLEncoder.encode(qrId, "utf-8") + "&flag="
							+ URLEncoder.encode(fromFlag, "utf-8");
					if (show_url != null) {
						redirectUrl += "&show_url=" + URLEncoder.encode(show_url, "utf-8");
					}
					sblog.append(
							String.format("info uid=%s,\n" + "qrId='%s'," + "\n" + "fromFlag='%s',"
									+ "\n" + "wxCode='%s'," + "\n", uid, qrId, fromFlag, code));
					sblog.append("------------ok------------");
					mLogger.info(sblog.toString());
					response.sendRedirect(redirectUrl);
					return;
				} catch (Exception e1) {
					e1.printStackTrace();
					msg = "连接wcf服务失败," + e1.getMessage();
					sblog.append(String.format("code=102,msg='%s'", msg));
					sblog.append("\n");
					sblog.append("------------error------------");
				}
			} catch (IOException e) {
				e.printStackTrace();
				msg = "连接微信服务器失败," + e.getMessage();
			} catch (JSONException e) {
				e.printStackTrace();
				msg = "微信服务器返回结果异常," + e.getMessage();
				sblog.append(String.format("code=103,msg='%s'", msg));
				sblog.append("\n");
				sblog.append("------------error------------");
			} catch (Exception e) {
				e.printStackTrace();
				msg = "查询不到相关人员," + e.getMessage();
				sblog.append(String.format("params: " + "qrId='%s'," + "\n" + "code='%s'," + "\n"
						+ "fromFlag='%s'," + "\n" + "msg='%s'," + "\n", qrId, code, fromFlag, msg));
				sblog.append("\n");
				sblog.append("------------error------------");
			}
			mLogger.warn(sblog.toString());
			response.sendRedirect(request.getContextPath() + "/Error.jsp" + "?errorCode=" + erCode
					+ "&msg=" + URLEncoder.encode(msg, "utf-8") + "&flag="
					+ URLEncoder.encode(fromFlag, "utf-8"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
