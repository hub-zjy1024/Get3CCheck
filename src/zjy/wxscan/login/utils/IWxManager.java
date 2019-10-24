package zjy.wxscan.login.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.b1b.tc.checker.utils.net.HttpUtils;

import zjy.wxscan.login.entity.BaseWxUserInfo;
import zjy.wxscan.login.entity.WxDepartmentInfo;

public class IWxManager {
	//	{
	//	    "userid": "zhangsan",
	//	    "name": "张三",
	//	    "alias": "jackzhang",
	//	    "mobile": "15913215421",
	//	    "department": [1, 2],
	//	    "order":[10,40],
	//	    "position": "产品经理",
	//	    "gender": "1",
	//	    "email": "zhangsan@gzdev.com",
	//	    "is_leader_in_dept": [1, 0],
	//	    "enable":1,
	//	    "avatar_mediaid": "2-G6nrLmr5EC3MNb_-zL1dDdzkd0p7cNliYu9V5w7o8K0",
	//	    "telephone": "020-123456",
	//	    "extattr": {
	//	        "attrs": [
	//	            {
	//	                "type": 0,
	//	                "name": "文本名称",
	//	                "text": {
	//	                    "value": "文本"
	//	                }
	//	            },
	//	            {
	//	                "type": 1,
	//	                "name": "网页名称",
	//	                "web": {
	//	                    "url": "http://www.test.com",
	//	                    "title": "标题"
	//	                }
	//	            }
	//	        ]
	//	    },
	//	    "to_invite": false,
	//	    "external_position": "高级产品经理",
	//	    "external_profile": {
	//	        "external_corp_name": "企业简称",
	//	        "external_attr": [
	//	            {
	//	                "type": 0,
	//	                "name": "文本名称",
	//	                "text": {
	//	                    "value": "文本"
	//	                }
	//	            },
	//	            {
	//	                "type": 1,
	//	                "name": "网页名称",
	//	                "web": {
	//	                    "url": "http://www.test.com",
	//	                    "title": "标题"
	//	                }
	//	            },
	//	            {
	//	                "type": 2,
	//	                "name": "测试app",
	//	                "miniprogram": {
	//	                    "appid": "wx8bd80126147df384",
	//	                    "pagepath": "/index",
	//	                    "title": "my miniprogram"
	//	                }
	//	            }
	//	        ]
	//	    }
	//	}

	public void addUser(String token, String Json) {

	}

	/*请求方式：POST（HTTPS）
	请求地址：https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN
	{
	"errcode": 0,
	"errmsg": "created"
	}
	*/ public static boolean deleteUser(String token, int uid) {
		//		请求方式：GET（HTTPS）
		//		请求地址：https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=USERID
		String url = ContentUrl.qyWxRootUrl + "/user/delete?access_token=%s&userid=%d";
		url = String.format(url, token, uid);
		try {
			String json = null;
			String postResult = HttpUtils.getGetResult(url, json);
			JSONObject retObj = new JSONObject(postResult);
			int retCode = retObj.getInt("errcode");
			if (retCode == 0) {
				return true;
			} else {
				throw new IOException("错误=" + retObj.getString("errmsg") + "\njson=" + json);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean addUser(String token, BaseWxUserInfo bUser) {
		//		String json = JSONObject.wrap(bUser).toString();
		JSONObject jobj = new JSONObject(bUser);
		int[] parts=bUser.getDepartment();
		jobj.put("department",parts);
		String json = jobj.toString();
		String url = ContentUrl.qyWxRootUrl + "/user/create?access_token=%s";
		System.out.println("addUserJson="+json);
		url = String.format(url, token);
		try {
			String postResult = HttpUtils.getPostResult(url, json);
			JSONObject retObj = new JSONObject(postResult);
			int retCode = retObj.getInt("errcode");
			if (retCode == 0) {
				return true;
			} else {
				throw new IOException("错误=" + retObj.getString("errmsg") + "\njson=" + json);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static class DepartmentManger {

		public static WxDepartmentInfo getWxPartIdByName(String token, String partName) {
			List<WxDepartmentInfo> mInfos = getAllParts(token, "");
			if (mInfos == null) {
				return null;
			}
			for (WxDepartmentInfo tInfo : mInfos) {
				String name = tInfo.getName();
				if (name.equals(partName)) {
					return tInfo;
				}
			}
			return null;
		}
		
//		请求方式：POST（HTTPS）
//		请求地址：https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=ACCESS_TOKEN
//		{
//			   "name": "广州研发中心",
//			   "parentid": 1,
//			   "order": 1,
//			   "id": 2
//			}
//			参数说明：
//
//			参数	必须	说明
//			access_token	是	调用接口凭证
//			name	是	部门名称。长度限制为1~32个字符，字符不能包括\:?”<>｜
//			parentid	是	父部门id，32位整型
		public static boolean addNew(String token, String partName) {
			String url = ContentUrl.qyWxRootUrl
					+ "/department/create?access_token=%s";
			url = String.format(url, token);
			String json="";
			JSONObject obj=new JSONObject();
			try {
				obj.put("name", partName);
				obj.put("parentid", 1);
				json=obj.toString();
				String postResult = HttpUtils.getPostResult(url, json);
				JSONObject jobj = new JSONObject(postResult);
				int retCode = jobj.getInt("errcode");
				if (retCode == 0) {
					return true;
				}else{
					throw new IOException("错误=" + jobj.getString("errmsg") + "\njson=" + json);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
			return false;
		}
		
		public static int addPart(String token, String partName) {
			String url = ContentUrl.qyWxRootUrl
					+ "/department/create?access_token=%s";
			url = String.format(url, token);
			String json="";
			JSONObject obj=new JSONObject();
			try {
				obj.put("name", partName);
				obj.put("parentid", 1);
				json=obj.toString();
				String postResult = HttpUtils.getPostResult(url, json);
				JSONObject jobj = new JSONObject(postResult);
				int retCode = jobj.getInt("errcode");
				if (retCode == 0) {
					int id=jobj.getInt("id");
					return id;
				}else{
					throw new IOException("错误=" + jobj.getString("errmsg") + "\njson=" + json);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
			return -1;
		}
		public static List<WxDepartmentInfo> getAllParts(String token, String partID) {
			String url = ContentUrl.qyWxRootUrl
					+ "/department/list?access_token=%s&id=%s";
			url = String.format(url, token, partID);
			List<WxDepartmentInfo> retList = new ArrayList<>();
			String postResult;
			try {
				String json=null;
				postResult = HttpUtils.getGetResult(url, json);
				JSONObject jobj = new JSONObject(postResult);
				int retCode = jobj.getInt("errcode");
				if (retCode == 0) {
					JSONArray mArray = jobj.getJSONArray("department");
					//System.out.println("Alldeparts="+mArray.toString());
					for (int i = 0; i < mArray.length(); i++) {
						JSONObject tempObj = mArray.getJSONObject(i);
						/*   "id": 2,
						   "name": "广州研发中心",
						   "parentid": 1,
						   "order": 10*/
						WxDepartmentInfo info = new WxDepartmentInfo();
						int id = tempObj.getInt("id");
						String name = tempObj.getString("name");
						int parentid = tempObj.getInt("parentid");
						int order = tempObj.getInt("order");
						info.setId(id);
						info.setName(name);
						info.setParentid(parentid);
						info.setOrder(order);
						retList.add(info);
					}
					return retList;
				} else {
					throw new IOException("错误=" + jobj.getString("errmsg") + "\njson=" + json);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
