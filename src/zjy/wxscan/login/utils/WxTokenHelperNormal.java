package zjy.wxscan.login.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import com.b1b.tc.checker.utils.net.HttpUtils;
import com.b1b.tc.checker.utils.net.StreamRead;

public class WxTokenHelperNormal {

	public static long lastTokenTime = System.currentTimeMillis();
	public static String corpid = "";
	public static String corpsecret = "";
	public static int agentID = -1;
	public static boolean isFirst = true;
	private static org.slf4j.Logger mLogger = LoggerFactory.getLogger(WxTokenHelperNormal.class);
	public static final String DEF_tokenPath = "/home/webData/Wx_ScanLogin/";
	public static final String DEF_tokenFileName = "wxNormalToken.txt";

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

	public synchronized static String getAppToken() throws IOException {
		String corpID = "wx3636754f0b243b3f";
		String corpsecret = "wJmyzAjXTliLbbxTv9bUJWiQ9tDIw20V8b2HIDmMBr31EugIYqtyawk7aAcgeLWB";
		return getToken(null, corpID, corpsecret);
	}

	public synchronized static String getToken(String corpID, String corpsecret)
			throws IOException {
		return getToken(null, corpID, corpsecret);
	}

	public synchronized static String getContactToken(HttpServletRequest req) throws IOException {
		String corpid = req.getServletContext().getInitParameter("corpid");
		String corpsecret = req.getServletContext().getInitParameter("corpsecret_contact");
		agentID = Integer.parseInt(req.getServletContext().getInitParameter("agentID"));
		return getToken(null, corpid, corpsecret);
	}

	public synchronized static String getContactToken() throws IOException {
		String corpid = "wx3636754f0b243b3f";
		String corpsecret = "wJmyzAjXTliLbbxTv9bUJWiQ9tDIw20V8b2HIDmMBr31EugIYqtyawk7aAcgeLWB";
		return getToken(null, corpid, corpsecret);
	}

	public synchronized static String getRawToken(String corpID, String corpsecret)
			throws IOException {
		String tokenPath = DEF_tokenPath + DEF_tokenFileName;
		String acUrl = String.format(
				"https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s", corpID,
				corpsecret);
		String acResponse = HttpUtils.getGetResult(acUrl, null);
		JSONObject jobj = new JSONObject(acResponse);
		String saveInfo = "";
		int code = jobj.getInt("errcode");
		if (code == 0) {
			String token = jobj.getString("access_token");
			int expires = jobj.getInt("expires_in");
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

	public static String pushText(HttpServletRequest req, String content, String toUser)
			throws Exception {
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
			mLogger.warn("[waring] error=" + msg);
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
				throw new Exception(msg);
			} else {
				code = "1";
				msg = "推送成功";
				String invalidUser = obj.getString("invaliduser");
				if (!"".equals(invalidUser)) {
					msg += "," + "无效用户" + invalidUser;
					mLogger.warn("[warning]push invalidUser:" + invalidUser);
				}
			}
			resultObj.put("code", code);
			resultObj.put("msg", msg);
		} catch (Exception e) {
			msg = "发送信息失败！！," + e.getMessage();
			e.printStackTrace();
			resultObj.put("code", code);
			resultObj.put("msg", msg);
			mLogger.warn("[error]push msg==" + msg);
			throw new Exception(msg);
		}
		return resultObj.toString();
	}

	public static void deleteOldToken() {
		String tokenPath = DEF_tokenPath + DEF_tokenFileName;
		File file = new File(tokenPath);
		if (file.exists()) {
			file.delete();
		}
	}

	private static String getToken(HttpServletRequest req, String corpid, String corpsecret)
			throws IOException {

		String tokenPath = DEF_tokenPath + DEF_tokenFileName;
		File file = new File(tokenPath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		long token_expires = 0;
		String token = "";
		if (file.exists()) {
			try {
				String tokenJson = StreamRead.readFromFile(tokenPath);
				JSONObject obj = new JSONObject(tokenJson);
				token = obj.getString("access_token");
				token_expires = obj.getLong("endTime");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (token.equals("")) {
			token = getNewToken(corpid, corpsecret);
			//			String acUrl = String.format(
			//					"https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s", corpid,
			//					corpsecret);
			//			String acResponse = HttpUtils.getGetResult(acUrl, null);
			//			JSONObject jobj = new JSONObject(acResponse);
			//			String saveInfo = "";
			//			int code = jobj.getInt("errcode");
			//			if (code == 0) {
			//				token = jobj.getString("access_token");
			//				int expires = jobj.getInt("expires_in");
			//				//				expires=5;
			//				long endTime = System.currentTimeMillis() + expires * 1000;
			//				jobj.put("endTime", endTime);
			//				saveInfo = jobj.toString();
			//				StreamRead.writeToFile(tokenPath, saveInfo);
			//				mLogger.info(String.format("get newToken,expired at '%s'",
			//						new Date(endTime).toLocaleString()));
			//			} else {
			//				throw new IOException(jobj.getString("errmsg"));
			//			}
		} else {
			if (System.currentTimeMillis() >= token_expires) {
				file.delete();
				mLogger.warn(String.format("Token expired,expired at '%s' now='%s'",
						new Date(token_expires).toLocaleString(), new Date().toLocaleString()));
				return getNewToken(corpid, corpsecret);
			}
		}
		return token;
	}

	public static String getNewToken(String corpid, String corpsecret) throws IOException {
		String acUrl = String.format(
				"https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s", corpid,
				corpsecret);
		String tokenPath = DEF_tokenPath + DEF_tokenFileName;
		String acResponse = "";
		try {
			acResponse = HttpUtils.getGetResult(acUrl, null);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("连接wx服务器失败," + e.getMessage());
		}
		String token = "";
		try {
			JSONObject jobj = new JSONObject(acResponse);
			String saveInfo = "";
			int code = jobj.getInt("errcode");

			if (code == 0) {
				token = jobj.getString("access_token");
				int expires = jobj.getInt("expires_in");
				long endTime = System.currentTimeMillis() + expires * 1000;
				jobj.put("endTime", endTime);
				saveInfo = jobj.toString();
				StreamRead.writeToFile(tokenPath, saveInfo);
				mLogger.info(String.format("get newToken,expired at '%s'",
						new Date(endTime).toLocaleString()));
			} else {
				throw new Exception("接口调用失败" + jobj.getString("errmsg"));
			}
		} catch (IOException e) {
			throw new IOException("其他错误," + e.getMessage());
		} catch (JSONException e) {
			throw new IOException("微信接口异常," + e.getMessage());
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
		return token;
	}

}
