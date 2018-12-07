package zjy.wxscan.login.utils;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

public class MFCMgr {

	/**
	 * 获取当前uid的所有品牌信息
	 * @param uid
	 * @return
	 */
	public String getPinpai(String uid) {
		try {
			return ChuKuServer.GetMFCInfoByCheckID(uid);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*	2：  GetMFCInfo（id，name） 得到品牌的信息，ID 是品牌ID，name 是品牌的名称，可以模糊查询。
		3： SetMFCCheckInfo(string id, string checkID, string checkTime) 维护凭票的审核人和审核时间，ID是品牌ID，check是审核人员ID，checkTIme 是审核时间。
	*/
	/**
	 * @param id 品牌ID
	 * @param name 品牌的名称
	 * @return 品牌的信息
	 */
	public String GetMFCInfo(String id, String name) {
		try {
			return ChuKuServer.GetMFCInfo(id, name);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 维护品牌的审核人和审核时间，ID是品牌ID，check是审核人员ID，checkTIme 是审核时间。
	 * @param id
	 * @param checkID
	 * @param checkTime
	 * @return
	 */
	public String SetMFCCheckInfo(String id, String checkID, String checkTime) {
		try {
			return ChuKuServer.SetMFCCheckInfo(id, checkID, checkTime);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return null;
	}
}
