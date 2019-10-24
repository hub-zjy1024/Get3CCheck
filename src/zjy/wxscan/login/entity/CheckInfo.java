package zjy.wxscan.login.entity;

public class CheckInfo {
	/*{"表":[{"PID":"1316440","制单日期":"2018/12/8 11:56:00","公司":"总公司","部门":"公司",
		"员工":"管理员","制单人":"管理员","单据类型":"正常销售","单据状态":"等待分公司经理审批","发货类型":"库房发货","型号":"TEST201800206002","数量":"
			1","进价":"1.0000","售价":"10.0000","成本":"1.0000","销售额":"10.0000","厂家":"718","封装":"封装","描述":"描述","明细备注":
			"","所属部门":"公司","客户编码":"101.001","客户":"何华荣测试公司","IsForeignClient":"False","开票公司":"","审核截止时间":"2018-12
			-15 12:15:44"}] }
			
	*/
	public CheckInfo() {
	}
	private String mxId;

	public CheckInfo(String pid, String createDate, String compName, String deptName, String epName,
			String createPerson, String type, String state, String fahuoType, String partno,
			String counts, String inPrice, String outPrice, String basePrice, String soldCounts,
			String factory, String fengzhuang, String description, String belongDept,
			String customerID, String customerName, String isForeignClient, String kaipiaoComp,
			String checkDeadLine) {
		super();
		this.pid = pid;
		this.createDate = createDate;
		this.compName = compName;
		this.deptName = deptName;
		this.epName = epName;
		this.createPerson = createPerson;
		this.type = type;
		this.state = state;
		this.fahuoType = fahuoType;
		this.partno = partno;
		this.counts = counts;
		this.inPrice = inPrice;
		this.outPrice = outPrice;
		this.basePrice = basePrice;
		this.soldCounts = soldCounts;
		this.factory = factory;
		this.fengzhuang = fengzhuang;
		this.description = description;
		this.belongDept = belongDept;
		this.customerID = customerID;
		this.customerName = customerName;
		IsForeignClient = isForeignClient;
		this.kaipiaoComp = kaipiaoComp;
		this.checkDeadLine = checkDeadLine;
	}

	private String pid;
	private String createDate;
	private String compName;
	private String deptName;
	private String epName;
	private String createPerson;
	private String type;
	private String state;
	private String fahuoType;
	private String partno;
	private String counts;
	private String inPrice;
	private String outPrice;
	private String basePrice;
	private String soldCounts;
	private String factory;
	private String fengzhuang;
	private String description;
	private String belongDept;
	private String customerID;
	private String customerName;
	private String IsForeignClient;
	private String kaipiaoComp;
	private String checkDeadLine;

	/*,"客户编码":"101.001","客户":"何华荣测试公司","IsForeignClient":"False","开票公司":"","审核截止时间":"2018-12
		-15 12:15:44"}] }"
		
				+ "
	*/

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getEpName() {
		return epName;
	}

	public void setEpName(String epName) {
		this.epName = epName;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFahuoType() {
		return fahuoType;
	}

	public void setFahuoType(String fahuoType) {
		this.fahuoType = fahuoType;
	}

	public String getPartno() {
		return partno;
	}

	public void setPartno(String partno) {
		this.partno = partno;
	}

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	public String getInPrice() {
		return inPrice;
	}

	public void setInPrice(String inPrice) {
		this.inPrice = inPrice;
	}

	public String getOutPrice() {
		return outPrice;
	}

	public void setOutPrice(String outPrice) {
		this.outPrice = outPrice;
	}

	public String getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}

	public String getSoldCounts() {
		return soldCounts;
	}

	public void setSoldCounts(String soldCounts) {
		this.soldCounts = soldCounts;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getFengzhuang() {
		return fengzhuang;
	}

	public void setFengzhuang(String fengzhuang) {
		this.fengzhuang = fengzhuang;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBelongDept() {
		return belongDept;
	}

	public void setBelongDept(String belongDept) {
		this.belongDept = belongDept;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getIsForeignClient() {
		return IsForeignClient;
	}

	public void setIsForeignClient(String isForeignClient) {
		IsForeignClient = isForeignClient;
	}

	public String getKaipiaoComp() {
		return kaipiaoComp;
	}

	public void setKaipiaoComp(String kaipiaoComp) {
		this.kaipiaoComp = kaipiaoComp;
	}

	public String getCheckDeadLine() {
		return checkDeadLine;
	}

	public void setCheckDeadLine(String checkDeadLine) {
		this.checkDeadLine = checkDeadLine;
	}

	public String getMxId() {
		return mxId;
	}

	public void setMxId(String mxId) {
		this.mxId = mxId;
	}
}
