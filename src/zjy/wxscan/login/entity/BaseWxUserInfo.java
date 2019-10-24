package zjy.wxscan.login.entity;

import java.util.List;

public class BaseWxUserInfo {
	protected String userid;
	protected String name;
	//	protected List<Integer> department;
	protected int[] department;
	

	protected String gender;
	//手机号码。企业内必须唯一，mobile/email二者不能同时为空
	protected String email;
	protected String mobile;
	private boolean to_invite = true;

	public BaseWxUserInfo() {
		super();
	}

	public BaseWxUserInfo(String userid, String name, int[] department, String gender, String email,
			String mobile) {
		super();
		this.userid = userid;
		this.name = name;
		this.department = department;
		this.gender = gender;
		this.email = email;
		this.mobile = mobile;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setDepartment(int[] department) {
		this.department = department;
	}
	public int[] getDepartment() {
		return department;
	}
	public boolean isTo_invite() {
		return to_invite;
	}

	public void setTo_invite(boolean to_invite) {
		this.to_invite = to_invite;
	}

}
