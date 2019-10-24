package zjy.wxscan.login.entity;

public class WxDepartmentInfo {
	/*   "id": 2,
	   "name": "广州研发中心",
	   "parentid": 1,
	   "order": 10*/
	private int id;
	private String name;
	private int parentid;
	private int order;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public int getParentid() {
		return parentid;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getOrder() {
		return order;
	}

}
