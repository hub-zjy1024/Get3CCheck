package zjy.wxscan.login.bussiness;

import java.io.IOException;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import com.b1b.tc.checker.utils.net.HttpUtils;

import zjy.wxscan.login.utils.ContentUrl;
import zjy.wxscan.login.utils.WxTokenHelperNormal;
import zjy.wxscan.login.utils.wcf.Login;

public class LoginMgr {
	private static Logger mLogger = Logger.getLogger("LoginMgr");

	public String changeQrLoginState(String uid, String qrId, String state)
			throws IOException, XmlPullParserException {
		String updateLogin = Login.SetUserCode(uid, qrId, state);
		return updateLogin;
	}

	public String GetUserInfoByAllInfoByID(String uid) throws IOException, XmlPullParserException {
		String users = Login.GetUserInfoByAllInfoByID(uid);
		return users;
	}

	public String getUserInfoFromWx(String code) throws Exception {
		String token = "";
		try {
			token = WxTokenHelperNormal.getAppToken();
			String url = ContentUrl.qyWxRootUrl + "/user/getuserinfo?";
			url += "access_token=" + token;
			url += "&code=" + code;
			String result = HttpUtils.getGetResult(url, null);
			// "UserId":"USERID",
			JSONObject obj = new JSONObject(result);
			if (obj.has("UserId")) {
				return obj.getString("UserId");
			} else {
				if (result.contains("40029")) {
//					WxTokenHelperNormal.getAppToken();
				}
				throw new Exception("getUserError=" + result + "\n"
						+ String.format("token='%s'\ncode='%s'", token, code));
			}
		} catch (IOException e) {
			e.printStackTrace();
			mLogger.warning("code=101,获取userid失败," + e.getMessage());
		}
		return null;
	}
}
