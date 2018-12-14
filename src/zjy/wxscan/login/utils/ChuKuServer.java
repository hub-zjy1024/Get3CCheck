package zjy.wxscan.login.utils;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.xmlpull.v1.XmlPullParserException;

public class ChuKuServer{
	private static String serverName = WebserviceUtils.ChuKuServer;
	public static String GetChuKuTongZhiInfoList(String checkWord,String uid,String stime,String etime,String pid,String partNo)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("checkWord", checkWord);
		properties.put("uid", uid);
		properties.put("stime", stime);
		properties.put("etime", etime);
		properties.put("pid", pid);
		properties.put("partNo", partNo);
		return WebserviceUtils.getWcfResult(properties, "GetChuKuTongZhiInfoList", serverName);
	}

	public static String GetChuKuInfoList(String checkWord,String uid,String stime,String etime,String pid,String partNo)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("checkWord", checkWord);
		properties.put("uid", uid);
		properties.put("stime", stime);
		properties.put("etime", etime);
		properties.put("pid", pid);
		properties.put("partNo", partNo);
		return WebserviceUtils.getWcfResult(properties, "GetChuKuInfoList", serverName);
	}

	public static String GetChuKuTongZhiInfo(String checkWord,String pid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("checkWord", checkWord);
		properties.put("pid", pid);
		return WebserviceUtils.getWcfResult(properties, "GetChuKuTongZhiInfo", serverName);
	}

	public static String GetChKuTongZhiDetailInfo(String checkWord,String pid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("checkWord", checkWord);
		properties.put("pid", pid);
		return WebserviceUtils.getWcfResult(properties, "GetChKuTongZhiDetailInfo", serverName);
	}

	public static String GetChuKuInfo(String checkWord,String pid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("checkWord", checkWord);
		properties.put("pid", pid);
		return WebserviceUtils.getWcfResult(properties, "GetChuKuInfo", serverName);
	}

	public static String GetChuKuDetailInfo(String checkWord,String pid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("checkWord", checkWord);
		properties.put("pid", pid);
		return WebserviceUtils.getWcfResult(properties, "GetChuKuDetailInfo", serverName);
	}

	public static String GetBILL_PictureRelatenfoByID(String checkWord,String ID)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("checkWord", checkWord);
		properties.put("ID", ID);
		return WebserviceUtils.getWcfResult(properties, "GetBILL_PictureRelatenfoByID", serverName);
	}

	public static String GetChuKuCheckInfoByTypeID(String checkWord,int typeid,String pid,String partNo,String uid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("checkWord", checkWord);
		properties.put("typeid", typeid);
		properties.put("pid", pid);
		properties.put("partNo", partNo);
		properties.put("uid", uid);
		return WebserviceUtils.getWcfResult(properties, "GetChuKuCheckInfoByTypeID", serverName);
	}

	public static String GetSetCheckInfo(String checkWord,int t,String info,String pid,int tp,String uname,String uid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("checkWord", checkWord);
		properties.put("t", t);
		properties.put("info", info);
		properties.put("pid", pid);
		properties.put("tp", tp);
		properties.put("uname", uname);
		properties.put("uid", uid);
		return WebserviceUtils.getWcfResult(properties, "GetSetCheckInfo", serverName);
	}

	public static String SetInsertPicInfo(String checkWord,int cid,int did,int uid,String pid,String filename,String filepath,String stypeID)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("checkWord", checkWord);
		properties.put("cid", cid);
		properties.put("did", did);
		properties.put("uid", uid);
		properties.put("pid", pid);
		properties.put("filename", filename);
		properties.put("filepath", filepath);
		properties.put("stypeID", stypeID);
		return WebserviceUtils.getWcfResult(properties, "SetInsertPicInfo", serverName);
	}

	public static String GetDataListForPanKu(String id,String part)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("id", id);
		properties.put("part", part);
		return WebserviceUtils.getWcfResult(properties, "GetDataListForPanKu", serverName);
	}

	public static String PanKu(int InstorageDetailID,String OldPartNo,int OldQuantity,String PKPartNo,String PKQuantity,String PKmfc,String PKDescription,String PKPack,String PKBatchNo,int MinPack,int OperID,String OperName,String DiskID,String Note,String PKPlace)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("InstorageDetailID", InstorageDetailID);
		properties.put("OldPartNo", OldPartNo);
		properties.put("OldQuantity", OldQuantity);
		properties.put("PKPartNo", PKPartNo);
		properties.put("PKQuantity", PKQuantity);
		properties.put("PKmfc", PKmfc);
		properties.put("PKDescription", PKDescription);
		properties.put("PKPack", PKPack);
		properties.put("PKBatchNo", PKBatchNo);
		properties.put("MinPack", MinPack);
		properties.put("OperID", OperID);
		properties.put("OperName", OperName);
		properties.put("DiskID", DiskID);
		properties.put("Note", Note);
		properties.put("PKPlace", PKPlace);
		return WebserviceUtils.getWcfResult(properties, "PanKu", serverName);
	}

	public static String CancelPanKuFlag(int instoragedetailPID)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("instoragedetailPID", instoragedetailPID);
		return WebserviceUtils.getWcfResult(properties, "CancelPanKuFlag", serverName);
	}

	public static String GetPauKuDataInfoByID(String id)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("id", id);
		return WebserviceUtils.getWcfResult(properties, "GetPauKuDataInfoByID", serverName);
	}

	public static String GetDataListForNaHuo(String uid,String part)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("uid", uid);
		properties.put("part", part);
		return WebserviceUtils.getWcfResult(properties, "GetDataListForNaHuo", serverName);
	}

	public static String GetDataInfoForNaHuoByID(String id,String main,String detail)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("id", id);
		properties.put("main", main);
		properties.put("detail", detail);
		return WebserviceUtils.getWcfResult(properties, "GetDataInfoForNaHuoByID", serverName);
	}

	public static String GetDataInfoByNaHuoMainID(String id)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("id", id);
		return WebserviceUtils.getWcfResult(properties, "GetDataInfoByNaHuoMainID", serverName);
	}

	public static String GetDataInfoByNaHuoDetailID(String id)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("id", id);
		return WebserviceUtils.getWcfResult(properties, "GetDataInfoByNaHuoDetailID", serverName);
	}

	public static String GetSupplierByUserID(String uid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("uid", uid);
		return WebserviceUtils.getWcfResult(properties, "GetSupplierByUserID", serverName);
	}

	//details=q1:ArrayOfstring
	//NotFormate Void:SaveMarketInfo
	@Deprecated
	public static String SaveMarketInfo(int id,String ProviderID)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("id", id);
		properties.put("ProviderID", ProviderID);
		return WebserviceUtils.getWcfResult(properties, "SaveMarketInfo", serverName);
	}

	public static String GetOutStorageNotifyPrintViewList(String beginDate,String endDate,String partNo,int pid,int uid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("beginDate", beginDate);
		properties.put("endDate", endDate);
		properties.put("partNo", partNo);
		properties.put("pid", pid);
		properties.put("uid", uid);
		return WebserviceUtils.getWcfResult(properties, "GetOutStorageNotifyPrintViewList", serverName);
	}

	public static String GetOutStoragePrintViewPriviceInfo(int pid,int uid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("pid", pid);
		properties.put("uid", uid);
		return WebserviceUtils.getWcfResult(properties, "GetOutStoragePrintViewPriviceInfo", serverName);
	}

	public static String GetOutStorageNotifyPrintView(int pid,int uid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("pid", pid);
		properties.put("uid", uid);
		return WebserviceUtils.getWcfResult(properties, "GetOutStorageNotifyPrintView", serverName);
	}

	public static String GetBillInfoByPID(int pid,int uid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("pid", pid);
		properties.put("uid", uid);
		return WebserviceUtils.getWcfResult(properties, "GetBillInfoByPID", serverName);
	}

	public static String UpdatePrintCKTZCount(int pid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("pid", pid);
		return WebserviceUtils.getWcfResult(properties, "UpdatePrintCKTZCount", serverName);
	}

	public static String GetInstorectInfo(int pid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("pid", pid);
		return WebserviceUtils.getWcfResult(properties, "GetInstorectInfo", serverName);
	}

	public static String GetInstorectInfoByMXID(int mxid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("mxid", mxid);
		return WebserviceUtils.getWcfResult(properties, "GetInstorectInfoByMXID", serverName);
	}

	//tb=q2:ArrayOfKeyValueOfanyTypeanyType
	//NotFormate Void:ReturnHashTableInfo
	@Deprecated
	public static String ReturnHashTableInfo()throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		return WebserviceUtils.getWcfResult(properties, "ReturnHashTableInfo", serverName);
	}

	public static String GetStorageBlanceInfoByID(int pid,String partno,String storageid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("pid", pid);
		properties.put("partno", partno);
		properties.put("storageid", storageid);
		return WebserviceUtils.getWcfResult(properties, "GetStorageBlanceInfoByID", serverName);
	}

	public static String GetStoreRoomIDByIP(String ip)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("ip", ip);
		return WebserviceUtils.getWcfResult(properties, "GetStoreRoomIDByIP", serverName);
	}

	public static String Shangjia(String detailID,String place,String kuQu,String operDescript,String uid,String ip)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("detailID", detailID);
		properties.put("place", place);
		properties.put("kuQu", kuQu);
		properties.put("operDescript", operDescript);
		properties.put("uid", uid);
		properties.put("ip", ip);
		return WebserviceUtils.getWcfResult(properties, "Shangjia", serverName);
	}

	public static String GetMFCInfoByCheckID(String uid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("uid", uid);
		return WebserviceUtils.getWcfResult(properties, "GetMFCInfoByCheckID", serverName);
	}

	public static String GetMFCInfo(String id,String name)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("id", id);
		properties.put("name", name);
		return WebserviceUtils.getWcfResult(properties, "GetMFCInfo", serverName);
	}

	public static String SetMFCCheckInfo(String id,String checkID,String checkTime)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("id", id);
		properties.put("checkID", checkID);
		properties.put("checkTime", checkTime);
		return WebserviceUtils.getWcfResult(properties, "SetMFCCheckInfo", serverName);
	}

	public static String GetVChuKuTongZhiBuID(String uid,String pid)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("uid", uid);
		properties.put("pid", pid);
		return WebserviceUtils.getWcfResult(properties, "GetVChuKuTongZhiBuID", serverName);
	}

	public static String GetVChuKuTongZhi2(String uid,String partno,String pid,String cj,boolean isCheck)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("uid", uid);
		properties.put("partno", partno);
		properties.put("pid", pid);
		properties.put("cj", cj);
		properties.put("isCheck", isCheck);
		return WebserviceUtils.getWcfResult(properties, "GetVChuKuTongZhi2", serverName);
	}

	public static String UpdateMFCCheckInfo(String pid,String mxID,String uid,String checkinfo,boolean IsOk)throws IOException, XmlPullParserException {
		LinkedHashMap<String, Object> properties = new LinkedHashMap<>();
		properties.put("pid", pid);
		properties.put("mxID", mxID);
		properties.put("uid", uid);
		properties.put("checkinfo", checkinfo);
		properties.put("IsOk", IsOk);
		return WebserviceUtils.getWcfResult(properties, "UpdateMFCCheckInfo", serverName);
	}

}