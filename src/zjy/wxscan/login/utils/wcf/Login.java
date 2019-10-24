package zjy.wxscan.login.utils.wcf;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.xmlpull.v1.XmlPullParserException;
public class Login{
	private static String serverName = WebserviceUtils.Login;
	//yzinfo=q1:YanZhengInfo
	//NotFormate Void:GetUserInfo
	@Deprecated
	public static String GetUserInfo(String uid,String pwd,String ip,String netWorkID,String mainBordID,String hostName,String diskID,String diskVolNum)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("uid", uid);
		properties.put("pwd", pwd);
		properties.put("ip", ip);
		properties.put("netWorkID", netWorkID);
		properties.put("mainBordID", mainBordID);
		properties.put("hostName", hostName);
		properties.put("diskID", diskID);
		properties.put("diskVolNum", diskVolNum);
		return WebserviceUtils.getWcfResult(properties, "GetUserInfo", serverName);
	}

	public static String GetUserInfoByID(String uid,String pwd,String ip,String netWorkID,String mainBordID,String hostName,String diskID,String diskVolNum)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("uid", uid);
		properties.put("pwd", pwd);
		properties.put("ip", ip);
		properties.put("netWorkID", netWorkID);
		properties.put("mainBordID", mainBordID);
		properties.put("hostName", hostName);
		properties.put("diskID", diskID);
		properties.put("diskVolNum", diskVolNum);
		return WebserviceUtils.getWcfResult(properties, "GetUserInfoByID", serverName);
	}

	public static String GetUserInfoByIDFordt(String uid,String pwd,String ip,String netWorkID,String mainBordID,String hostName,String diskID,String diskVolNum)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("uid", uid);
		properties.put("pwd", pwd);
		properties.put("ip", ip);
		properties.put("netWorkID", netWorkID);
		properties.put("mainBordID", mainBordID);
		properties.put("hostName", hostName);
		properties.put("diskID", diskID);
		properties.put("diskVolNum", diskVolNum);
		return WebserviceUtils.getWcfResult(properties, "GetUserInfoByIDFordt", serverName);
	}

	public static String GetUserInfoByUserID(String checker,String uid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("checker", checker);
		properties.put("uid", uid);
		return WebserviceUtils.getWcfResult(properties, "GetUserInfoByUserID", serverName);
	}

	public static String GetUserInfoByUID(String checker,String uid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("checker", checker);
		properties.put("uid", uid);
		return WebserviceUtils.getWcfResult(properties, "GetUserInfoByUID", serverName);
	}

	public static String GetCCInfoByUserID(String uid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("uid", uid);
		return WebserviceUtils.getWcfResult(properties, "GetCCInfoByUserID", serverName);
	}

	public static String GetUserInfoByAllInfo()throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		return WebserviceUtils.getWcfResult(properties, "GetUserInfoByAllInfo", serverName);
	}

	public static String GetUserInfoByAllInfoByID(String id)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("id", id);
		return WebserviceUtils.getWcfResult(properties, "GetUserInfoByAllInfoByID", serverName);
	}

	public static String UpdateUserPwd(String uid,String pwd,String oldpwd)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("uid", uid);
		properties.put("pwd", pwd);
		properties.put("oldpwd", oldpwd);
		return WebserviceUtils.getWcfResult(properties, "UpdateUserPwd", serverName);
	}

	public static String SetCCInfo(String Name,String DownPath,String A_Downpic,String A_NDownpic,String CC_path,String A_bm)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("Name", Name);
		properties.put("DownPath", DownPath);
		properties.put("A_Downpic", A_Downpic);
		properties.put("A_NDownpic", A_NDownpic);
		properties.put("CC_path", CC_path);
		properties.put("A_bm", A_bm);
		return WebserviceUtils.getWcfResult(properties, "SetCCInfo", serverName);
	}

	public static String GetCCInfoAll()throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		return WebserviceUtils.getWcfResult(properties, "GetCCInfoAll", serverName);
	}

	public static String InsertIntoCC(String pid,String filenames,String downpath)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("pid", pid);
		properties.put("filenames", filenames);
		properties.put("downpath", downpath);
		return WebserviceUtils.getWcfResult(properties, "InsertIntoCC", serverName);
	}

	public static String DeleteCCInfo(String A_ID)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("A_ID", A_ID);
		return WebserviceUtils.getWcfResult(properties, "DeleteCCInfo", serverName);
	}

	public static String GetUserMenuInfoAll()throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		return WebserviceUtils.getWcfResult(properties, "GetUserMenuInfoAll", serverName);
	}

	public static String GetUserTreeInfo()throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		return WebserviceUtils.getWcfResult(properties, "GetUserTreeInfo", serverName);
	}

	public static String DeleteUserMenuByUserID(String uid,String A_ID,String st)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("uid", uid);
		properties.put("A_ID", A_ID);
		properties.put("st", st);
		return WebserviceUtils.getWcfResult(properties, "DeleteUserMenuByUserID", serverName);
	}

	public static String GetUserInfoByIDCode(String id,String code)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("id", id);
		properties.put("code", code);
		return WebserviceUtils.getWcfResult(properties, "GetUserInfoByIDCode", serverName);
	}
	
	public static String SetUserCode(String id,String code,String stype)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("id", id);
		properties.put("code", code);
		properties.put("stype", stype);
		return WebserviceUtils.getWcfResult(properties, "SetUserCode", serverName);
	}


}