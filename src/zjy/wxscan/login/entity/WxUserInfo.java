package zjy.wxscan.login.entity;

import java.util.Date;
import java.util.List;

public class WxUserInfo extends BaseWxUserInfo{
	private String userid;
	private String name;
	private String alias;
	private String mobile;
	private Date department;
	private List<Integer> order;
	private String position;
	private String gender;
	private String email;
	/*private List<int>is_leader_in_dept;*/
	private int enable;
	private String avatar_mediaid;
	private String telephone;
	/*private Extattr extattr;*/
	private boolean to_invite;
	private String external_position;
	/*private External_profile external_profile;*/

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserid() {
		return userid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setDepartment(Date department) {
		this.department = department;
	}

//	public Date getDepartment() {
//		return department;
//	}


	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition() {
		return position;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}


	public void setEnable(int enable) {
		this.enable = enable;
	}

	public int getEnable() {
		return enable;
	}

	public void setAvatar_mediaid(String avatar_mediaid) {
		this.avatar_mediaid = avatar_mediaid;
	}

	public String getAvatar_mediaid() {
		return avatar_mediaid;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephone() {
		return telephone;
	}


	public void setTo_invite(boolean to_invite) {
		this.to_invite = to_invite;
	}

	public boolean getTo_invite() {
		return to_invite;
	}

	public void setExternal_position(String external_position) {
		this.external_position = external_position;
	}

	public String getExternal_position() {
		return external_position;
	}
	//mobile	否	手机号码。企业内必须唯一，mobile/email二者不能同时为空
/*	参数说明：
	参数	必须	说明
	access_token	是	调用接口凭证。获取方法查看“获取access_token”
	userid	是	成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节
	name	是	成员名称。长度为1~64个utf8字符
	alias	否	成员别名。长度1~32个utf8字符
	mobile	否	手机号码。企业内必须唯一，mobile/email二者不能同时为空
	department	是	成员所属部门id列表,不超过20个
	order	否	部门内的排序值，默认为0，成员次序以创建时间从小到大排列。数量必须和department一致，数值越大排序越前面。有效的值范围是[0, 2^32)
	position	否	职务信息。长度为0~128个字符
	gender	否	性别。1表示男性，2表示女性
	email	否	邮箱。长度6~64个字节，且为有效的email格式。企业内必须唯一，mobile/email二者不能同时为空
	telephone	否	座机。32字节以内，由纯数字或’-‘号组成。
	is_leader_in_dept	否	个数必须和department一致，表示在所在的部门内是否为上级。1表示为上级，0表示非上级。在审批等应用里可以用来标识上级审批人
	avatar_mediaid	否	成员头像的mediaid，通过素材管理接口上传图片获得的mediaid
	enable	否	启用/禁用成员。1表示启用成员，0表示禁用成员
	extattr	否	自定义字段。自定义字段需要先在WEB管理端添加，见扩展属性添加方法，否则忽略未知属性的赋值。与对外属性一致，不过只支持type=0的文本和type=1的网页类型，详细描述查看对外属性
	to_invite	否	是否邀请该成员使用企业微信（将通过微信服务通知或短信或邮件下发邀请，每天自动下发一次，最多持续3个工作日），默认值为true。
	external_profile	否	成员对外属性，字段详情见对外属性
	external_position	否	对外职务，如果设置了该值，则以此作为对外展示的职务，否则以position来展示。长度12个汉字内
*/}
