package zjy.wxscan.login.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import sun.tools.jar.resources.jar;
import zjy.wxscan.login.entity.CheckInfo;

public class MFCMgr {

	private static final Logger mLogger = Logger.getLogger(MFCMgr.class.getName());

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
	 *  GetMFCInfo（id，name） 得到品牌的信息，ID 是品牌ID，name 是品牌的名称，可以模糊查询。
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

	/**
	 * 	通过用户ID和PID得到明细信息
	 * @param uid 用户ID  （不能为空）
	 * @param pid 单据号ID（不能为空）
	 * @return
	 */
	public String GetVChuKuTongZhiBuID(String uid, String pid) {
		try {
			return ChuKuServer.GetVChuKuTongZhiBuID(uid, pid);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 	审核操作
	 * @param pid 单据号ID（不能为空）
	 * @param mxID 明细号ID（多个明细好ID用逗号【,】分开）
	 * @param uid 用户ID 
	 * @param checkinfo 审核信息:包含时间，用户信息，审核信息等，审核不通过的时候必须填写不通过原因
	 * @param IsOk 是否通过，true 表示通过，false表示不通过
	 * @return
	 */
	public String UpdateMFCCheckInfo(String pid, String mxID, String uid, String checkinfo,
			boolean IsOk) {
		try {
			return ChuKuServer.UpdateMFCCheckInfo(pid, mxID, uid, checkinfo, IsOk);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过查询得到数据列表信息
	 * @param uid 用户ID（不能为空）
	 * @param partno 型号
	 * @param pid 单据号ID
	 * @param cj 厂家
	 * @param isCheck 是否审核 （true标识查询已经审核的数据，false表示查询未审核的数据）
	 * @return
	 */
	public String GetVChuKuTongZhi2(String uid, String partno, String pid, String cj,
			boolean isCheck) {
		String rJson = "{\"code\":%d,\"data\":%s}";
		String fJson = "";
		List<CheckInfo> list = GetVChuKuTongZhi2List(uid, partno, pid, cj, isCheck);
		if (list != null) {
			try {
				fJson = String.format(rJson, 1, (String) JSONObject.wrap(list));
			} catch (Exception e) {
				fJson = String.format(rJson, 0, "\"查询失败," + e.getMessage() + "\"");
			}
		} else {
			fJson = String.format(rJson, 0, "\"查询失败\"");
		}
		return fJson;
	}

	public List<CheckInfo> GetVChuKuTongZhi2List(String uid, String partno, String pid, String cj,
			boolean isCheck) {
		String jsondata = "";
		try {
			List<CheckInfo> list = new ArrayList<>();
			jsondata = ChuKuServer.GetVChuKuTongZhi2(uid, partno, pid, cj, isCheck);
			//jsondata = "{\"表\":[{\"PID\":\"1316440\",\"制单日期\":\"2018/12/8 11:56:00\",\"公司\":\"总公司\",\"部门\":\"公司\",\"员工\":\"管理员\",\"制单人\":\"管理员\",\"单据类型\":\"正常销售\",\"单据状态\":\"等待分公司经理审批\",\"发货类型\":\"库房发货\",\"型号\":\"TEST201800206002\",\"数量\":\"1\",\"进价\":\"1.0000\",\"售价\":\"10.0000\",\"成本\":\"1.0000\",\"销售额\":\"10.0000\",\"厂家\":\"718\",\"封装\":\"封装\",\"描述\":\"描述\",\"明细备注\":\"\",\"所属部门\":\"公司\",\"客户编码\":\"101.001\",\"客户\":\"何华荣测试公司\",\"IsForeignClient\":\"False\",\"开票公司\":\"\",\"审核截止时间\":\"2018-12-15 12:15:44\"}]}";
			JSONObject jobj = new JSONObject(jsondata);
			JSONArray jArr = jobj.getJSONArray("表");
			for (int i = 0; i < jArr.length(); i++) {
				JSONObject tobj = jArr.getJSONObject(i);
				//				{"表":[{"PID":"1316440","制单日期":"2018/12/8 11:56:00","公司":"总公司","部门":"公司",
				//					"员工":"管理员","制单人":"管理员","单据类型":"正常销售","单据状态":"等待分公司经理审批","发货类型":"库房发货","型号":"TEST201800206002","数量":"
				//						1","进价":"1.0000","售价":"10.0000","成本":"1.0000","销售额":"10.0000","厂家":"718","封装":"封装","描述":"描述","明细备注":
				//						"","所属部门":"公司","客户编码":"101.001","客户":"何华荣测试公司","IsForeignClient":"False","开票公司":"","审核截止时间":"2018-12
				//						-15 12:15:44"}] }
				String PID = tobj.getString("PID");
				String createDate = tobj.getString("制单日期");
				String compName = tobj.getString("公司");
				String deptName = tobj.getString("部门");
				String epName = tobj.getString("员工");
				String createPerson = tobj.getString("制单人");
				String type = tobj.getString("单据类型");
				String state = tobj.getString("单据状态");
				String fahuoType = tobj.getString("发货类型");
				String partno2 = tobj.getString("型号");
				String counts = tobj.getString("数量");
				String inPrice = tobj.getString("进价");
				String outPrice = tobj.getString("售价");
				String basePrice = tobj.getString("成本");
				String soldCounts = tobj.getString("销售额");
				String factory = tobj.getString("厂家");
				String fengzhuang = tobj.getString("封装");
				String description = tobj.getString("描述");
				String belongDept = tobj.getString("所属部门");
				String customerID = tobj.getString("客户编码");
				String customerName = tobj.getString("客户");
				String IsForeignClient = tobj.getString("IsForeignClient");
				String kaipiaoComp = tobj.getString("开票公司");
				String checkDeadLine = tobj.getString("审核截止时间");
				CheckInfo info = new CheckInfo(PID, createDate, compName, deptName, epName,
						createPerson, type, state, fahuoType, partno2, counts, inPrice, outPrice,
						basePrice, soldCounts, factory, fengzhuang, description, belongDept,
						customerID, customerName, IsForeignClient, kaipiaoComp, checkDeadLine);
				list.add(info);
			}
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			mLogger.warning("\nJsonEx=" + e.getMessage() + "\njson=" + jsondata);
		} catch (Exception e) {
			mLogger.warning("\nEx=" + e.getMessage() + "\njson=" + jsondata);
		}
		return null;
	}
}
