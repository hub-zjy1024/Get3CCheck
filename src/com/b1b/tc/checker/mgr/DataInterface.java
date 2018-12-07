package com.b1b.tc.checker.mgr;

public interface DataInterface {
	public String getData(String partno);

	public String getDetail(String id, String type);

	public String addNewData(String dType, String mType, String PartNo, String type, String uid,
			String uname);
	
	public String login(String uid, String pwd) ;
}
