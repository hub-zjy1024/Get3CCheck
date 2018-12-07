package zjy.wxscan.login;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.b1b.tc.checker.utils.net.HttpUtils;
import com.b1b.tc.checker.utils.net.StreamRead;

public class WxTokenHelper {

	public static long lastTokenTime = System.currentTimeMillis();
	public static String corpid = "";
	public static String corpsecret = "";
	public static int agentID = -1;
	public static boolean isFirst = true;
	private static Logger mLogger = Logger.getLogger("WxTokenHelper");

	//	GET（HTTPS）
	//	请求URL：https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRECT
	public synchronized static String getToken(HttpServletRequest req) throws IOException {
		if (corpid.equals("") || corpsecret.equals("")) {
			corpid = req.getServletContext().getInitParameter("corpid");
			corpsecret = req.getServletContext().getInitParameter("corpsecret");
			agentID = Integer.parseInt(req.getServletContext().getInitParameter("agentID"));
		}
		return getToken(req, corpid, corpsecret);
	}

	public synchronized static String getToken(String corpID, String corpsecret)
			throws IOException {
		return getToken(null, corpID, corpsecret);
	}

	public static String pushText(HttpServletRequest req, String content, String toUser) {
		JSONObject resultObj = new JSONObject();
		String code = "0";
		String msg = "";
		String token = "";
		try {
			token = getToken(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (token.equals("")) {
			resultObj.put("code", code);
			msg = "获取Token失败！！";
			mLogger.warning("[waring] error=" + msg);
			resultObj.put("msg", msg);
			return resultObj.toString();
		}
		String testContent = "来自【大赢家的测试】。\n<a href=\\\"http://work.weixin.qq.com\\\">测试</a>"
				+ "，测试时间：" + new SimpleDateFormat("HH:mm:ss").format(new Date());
		String toparty = "";
		content += "\n发送时间:" + new SimpleDateFormat("HH:mm:ss").format(new Date());
		String pushUrl = String
				.format("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=%s", token);
		String msgJons = "{\"touser\" :" + "\"" + toUser + "\",\"toparty\":\"" + toparty
				+ "\",\"totag\":\"\",\"msgtype\":\"text\",\"agentid\":" + agentID
				+ ",\"text\":{\"content\":\"" + content + "\"},\"safe\":0}";
		String pushRsp = "";
		try {
			pushRsp = HttpUtils.getPostResult(pushUrl, msgJons);
			JSONObject obj = new JSONObject(pushRsp);
			int returnCode = obj.getInt("errcode");
			if (returnCode != 0) {
				msg = obj.getString("errmsg");
				mLogger.warning("[error]code==:" + returnCode + ",detail:" + msg);
			} else {
				code = "1";
				msg = "推送成功";
				String invalidUser = obj.getString("invaliduser");
				if (!"".equals(invalidUser)) {
					msg += "," + "无效用户" + invalidUser;
					mLogger.warning("[warning]invalidUser:" + invalidUser);
				}
			}
			mLogger.info("[info] pushTo " + toUser + ",ret=" + msg);
			resultObj.put("code", code);
			resultObj.put("msg", msg);
		} catch (Exception e) {
			msg = "发送信息失败！！," + e.getMessage();
			resultObj.put("code", code);
			resultObj.put("msg", msg);
			e.printStackTrace();
		}
		return resultObj.toString();
	}

	public static void deleteOldToken(HttpServletRequest req) {
		String rootPath = "";
		if (req != null) {
			rootPath = req.getServletContext().getRealPath("");
		}
		String tokenPath = rootPath + "wxToken.txt";
		File file = new File(tokenPath);
		if (file.exists()) {
			file.delete();
		}
	}

	public static void main(String[] args) {
		String corpid = "wx3636754f0b243b3f";
		String corpsecret = "wJmyzAjXTliLbbxTv9bUJWiQ9tDIw20V8b2HIDmMBr31EugIYqtyawk7aAcgeLWB";

		try {
			String token = getToken(null, corpid, corpsecret);
			getDepartments(token);
			getUser(token);
			//			int agentID=5;
			int agentID = 8;
			//			String toUser = "3548";
			//			String toUser = "3693";
			//			String toPart = "2";
			String toUser = "3693";
			String toparty = "";
			String pushUrl = String.format(
					"https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=%s", token);
			String msgJons = "{\"touser\" :" + "\"" + toUser + "\",\"toparty\":\"" + toparty
					+ "\",\"totag\":\"\",\"msgtype\":\"text\",\"agentid\":" + agentID
					+ ",\"text\":{\"content\":\"来自【大赢家的测试】。\n<a href=\\\"http://work.weixin.qq.com\\\">测试</a>"
					+ "，测试时间：" + new SimpleDateFormat("HH:mm:ss").format(new Date())
					+ "\"},\"safe\":0}";
			String pushRsp = HttpUtils.getPostResult(pushUrl, msgJons);
			//			{"errcode":0,"errmsg":"ok","invaliduser":"","invalidparty":"2"}
			JSONObject obj = new JSONObject(pushRsp);
			if (obj.getInt("errcode") != 0) {
				String invalidUser = obj.getString("invaliduser");
				String invalidParty = obj.getString("invalidparty");
				if (!invalidUser.equals("")) {
					System.out.println("无效的员工ID：" + invalidUser);
				}
				if (!invalidParty.equals("")) {
					System.out.println("无效的部门ID：" + invalidParty);
				}
			}
			System.out.println("pushRsp:" + pushRsp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getToken(HttpServletRequest req, String corpid, String corpsecret)
			throws IOException {
		String acUrl = String.format(
				"https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s", corpid,
				corpsecret);
		String tokenPath = "";
		if (req != null) {
			tokenPath = req.getServletContext().getRealPath("wxToken.txt");
		} else {
			tokenPath = "/home/webData/Wx/wxToken.txt";
		}
		File file = new File(tokenPath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		long token_expires = 0;
		String token = "";
		try {
			String tokenJson = StreamRead.readFromFile(tokenPath);
			JSONObject obj = new JSONObject(tokenJson);
			token = obj.getString("access_token");
			token_expires = obj.getLong("endTime");
			if (System.currentTimeMillis() >= token_expires) {
				mLogger.info(String.format("Token expired"));
				//file.delete();
				//	return getToken(req, corpid, corpsecret);
				throw new Exception("wxToken expired");
			} else {
				return token;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				return tokenHttp(acUrl, tokenPath);
			} catch (Exception e2) {
				mLogger.info(String.format("get newToken error, '%s'", e2.getMessage()));
			}
		}
		return token;
	}

	public static String tokenHttp(String acUrl, String tokenPath) throws Exception {
		String acResponse = HttpUtils.getGetResult(acUrl, null);
		JSONObject jobj = new JSONObject(acResponse);
		String saveInfo = "";
		int code = jobj.getInt("errcode");
		if (code == 0) {
			String token = jobj.getString("access_token");
			int expires = jobj.getInt("expires_in");
			//				expires=5;
			long endTime = System.currentTimeMillis() + expires * 1000;
			jobj.put("endTime", endTime);
			saveInfo = jobj.toString();
			StreamRead.writeToFile(tokenPath, saveInfo);
			mLogger.info(String.format("get newToken,expired at '%s'",
					new Date(endTime).toLocaleString()));
			return token;
		} else {
			throw new IOException(jobj.getString("errmsg"));
		}
	}

	public static void getDepartments(String token) throws IOException {
		String url = String.format(
				"https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=%s&id=", token);
		String getResult = HttpUtils.getGetResult(url, null);
		System.out.println(String.format("departments:%s", getResult));
	}

	public static String getUser(String token) throws IOException {
		//		请求方式：GET（HTTPS）
		String url = String.format(
				"https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=%s&userid=3693", token);
		String getResult = HttpUtils.getGetResult(url, null);
		System.out.println(String.format("userinfo:%s", getResult));
		return getResult;
	}

	public static String getAllDepartmentUser(String token) throws IOException {
		//		请求方式：GET（HTTPS）
		String url = String.format(
				"https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=%s&department_id=1&fetch_child=1",
				token);
		String getResult = HttpUtils.getGetResult(url, null);
		//		System.out.println(String.format("ALLUsers:%s", getResult));
		return getResult;
	}
}
