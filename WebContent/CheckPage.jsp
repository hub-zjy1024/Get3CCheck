<%@page import="zjy.wxscan.login.entity.CheckInfo"%>
<%@page import="java.util.List"%>
<%@page import="zjy.wxscan.login.utils.MFCMgr"%>
<%@page import="java.util.logging.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>待审批单据</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<script type="text/javascript" src="./js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="./js/cookie_utils.js"></script>
<link rel="stylesheet" href="./css/common.css" type="text/css" />
<style type="text/css">
input {
	width: 100px;
	height: 40px;
	font-size: 20px;
}

table {
	width: 700px;
	border: solid 1px black;
	overflow: auto;
	text-align: left;
	font-size: 25px;
}

table td {
	max-height: 100px;
	border-left: solid 1px #FF5722;
}

table tr td:FIRST-OF-TYPE {
	max-height: 100px;
	border-left: none;
}

th {
	border-bottom: solid 2px;
	min-width: 70px;
}

tr td {
	line-height: 50px;
	border-bottom: solid 1px;
}

.data_editor_wrapper {
	width: 100px;
	overflow: visible;
}

.data_editor {
	position: relative;
	font-size: 20px;
	display: none;
	line-height: 1.5em;
	border: solid 1px #00BCD4;
	box-shadow: 2px 2px 6px 0px;
	padding: 5px 5px;
	line-height: 1.5em;
	background: white;
	width: 310px;
	left: -290px;
	top: 90px;
}

.data_editor p {
	margin: 5px 0;
	color: red;
}

.data_editor input {
	width: 146px;
	height: 40px;
	font-size: 20px;
}

.data_editor input[type=button] {
	background: #00b2ce;
	color: white;
	width: 110px;
}

h1 {
	color: #ffffff;
	background-color: #31a59a;
	width: 170px;
}

.cls_h1 {
	color: #ffffff;
	background-color: #31a59a;
}
</style>
<script type="text/javascript">
	function searchData(uid, pid, partno, isCheck, cj) {
		/* String uid = request.getParameter("uid");
		String partno = request.getParameter("partno");
		String pid = request.getParameter("pid");
		String cj = request.getParameter("cj");
		String checkFalg = request.getParameter("isCheck"); */
		var nUrl = '' + window.location;
		console.log("nUrl=" + nUrl);
		//http://192.168.10.66:8080/Get3CCheck/CheckPage.jsp?pid=1316440
		var end = nUrl.lastIndexOf('jsp');
		var mainUrl = nUrl.substring(0, end + 3);
		console.log("mainUrl=" + mainUrl);
		var murl = mainUrl + "?";
		murl += "flag=search";
		murl += "&uid=" + uid;
		murl += "&partno=" + partno;
		murl += "&pid=" + pid;
		murl += "&isCheck=" + isCheck;
		murl += "&cj=" + cj;
		window.location = murl;
		//http://192.168.10.66:8080/Get3CCheck/CheckPage.jsp
		/* $.ajax({
			url : murl,
			type : "get",
			dataType : "json",
			success : function(retData) {
				var code = retData.code;
				if (code == 1) {
					var mdata = retData.data;
					for (var i = 0; i < mdata.length; i++) {
						console.log(mdata[i]); // a  b  c 
					}
				} else {
					alert("查询不到相关信息");
				}
				console.log("ok  ok ok");
			},
			error : function(e1, e2, e3) {
				alert("网络错误！！！" + e1);
			}

		}); */
	}
	function startSearch() {
		var pid = $("#check_pid").val();
		var partno = $("#check_partno").val();
		var uid = "101";
		var isCheck = "0";
		//$("#cbox0").attr("checked", true);
		var cboState = $("input[type='checkbox']").is(':checked');
		if (cboState) {
			isCheck = "1";
		}
		//alert("cbo state=" + cboState);
		var cj = "";
		searchData(uid, pid, partno, isCheck, cj);
	}
	//	(string pid, string mxID, string uid, string checkinfo, bool IsOk)
	function setCheckInfo(index, pid, mxID, uid, isOk) {
		console.log("now id=" + index);
		var parent = $(".checkToolbar:eq(" + index + ")");
		var uid = getCookie("wxid");
		var checkinfo = parent.find("[id=id_checkMsg]").val();
		var IsOk = "";
		var murl = "./CheckPageServlet?";
		murl += "flag=check";
		murl += "&pid=" + pid;
		murl += "&mxID=" + mxID;
		murl += "&uid=" + uid;
		/* 	murl += "&checkinfo=" + encodeURIComponent(checkinfo); */
		murl += "&IsOk=" + isOk;
		if ('' == checkinfo && "0" == isOk) {
			alert("请输入不通过时的审核信息");
			return;
		}
		$.ajax({
			url : murl,
			//type : "get",
			type : "post",
			dataType : "text",
			data : checkinfo,
			contentType : "",
			success : function(retData) {
				/* 	var code = retData.code;
					if (code == 1) {
						var mdata = retData.data;
						for (var i = 0; i < mdata.length; i++) {
							console.log(mdata[i]); // a  b  c 
						}
					} else {
						alert("查询不到相关信息");
					} */
				if (isOk == '1') {
					if (retData == '1') {
						alert("审核成功");
					} else {
						alert("审核失败！！！！！");
					}
				} else {
					if (retData == '2') {
						alert("审核成功");
					} else {
						alert("审核失败！！！！！");
					}
				}
				console.log("checkRet=" + retData);
			},
			error : function(e1, e2, e3) {
				alert("网络错误！！！" + e1);
			}

		});
	}
</script>

</head>

<body>
	<div class="containner">
		<h1>待审批单据</h1>
		<%
			String sId = request.getParameter("pid");
			String spartno = request.getParameter("partno");
			if (sId == null) {
				sId = "";
			}
			if (spartno == null) {
				spartno = "";
			}
		%>
		<div class="searchbar">
			单据号： <input id="check_pid" placeholder="单据号" value='<%=sId%>'
				oninput="value=value.replace(/[^\d]/g,'')"></input> 型号： <input
				id="check_partno" placeholder="型号" value='<%=spartno%>'></input> <input
				id="check_cbo" type="checkbox" value="显示已审核过"
				style="width: 30px; height: 20px;">显示已审核过<input
				type="button" style="margin-left: 10px;" value="搜索"
				onclick="startSearch();">
		</div>
		<%
			Logger jspLogg = Logger.getLogger("mfc");
			String wxId = "";
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					String cName = c.getName();
					if ("wxid".equals(cName)) {
						wxId = c.getValue();
					}
				}
			}
			if ("".equals(wxId)) {
		%>
		<p style="font-size: 20px">请先登录</p>
		<%
			//writer.append("<p style=\"font-size:20px\">请先登录</p>");
			} else {
		%>
		<p style="font-size: 20px">
			当前登录id=<%=wxId%>
		</p>
		<%
			String para_pid = request.getParameter("pid");
				MFCMgr mgr = new MFCMgr();
				if (para_pid != null) {
					List<CheckInfo> list = mgr.GetVChuKuTongZhi2List(wxId, "", para_pid, "", false);
					if (list == null) {
						return;
					}
		%>
		<!-- 	"型号":"TEST201800206002","数量":"1","进价":"1.0000","售价":"10.0000","成本":"1.0000","销售额":"10.0000","厂家":"718","封装":"封装","描述":"描述"
	 -->
		<table class="table.cs">
			<thead>
				<tr>
					<th>pid</th>
					<th>型号</th>
					<th>数量</th>
					<th>进价</th>
					<th>售价</th>
					<th>成本</th>
					<th>销售额</th>
					<th>厂家</th>
					<th>封装</th>
					<th>描述</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (int i = 0; i < list.size(); i++) {
								CheckInfo tempInfo = list.get(i);
				%>
				<tr>
					<td><%=tempInfo.getPid()%></td>
					<td><%=tempInfo.getPartno()%></td>
					<td><%=tempInfo.getCounts()%></td>
					<td><%=tempInfo.getInPrice()%></td>
					<td><%=tempInfo.getOutPrice()%></td>
					<td><%=tempInfo.getBasePrice()%></td>
					<td><%=tempInfo.getSoldCounts()%></td>
					<td><%=tempInfo.getFactory()%></td>
					<td><%=tempInfo.getFengzhuang()%></td>
					<td><%=tempInfo.getDescription()%></td>
				</tr>
				<tr class="checkToolbar">
					<td colspan="10">审核信息：<input id="id_checkMsg"
						placeholder="审核信息（不通过时需填写）"> <a
						href="javascript:setCheckInfo(<%=i%>,'<%=tempInfo.getPid()%>','<%=tempInfo.getMxId()%>','','1')">通过</a>
						<a
						href="javascript:setCheckInfo(<%=i%>,'<%=tempInfo.getPid()%>','<%=tempInfo.getMxId()%>','','0')">不通过</a></td>
				</tr>
				<%
					}
				%>
				<!-- 	<tr>
					<td>asdfjasdklfjal;sdjfl; asdjl asjdlf jasdf asd fasd</td>
					<td>拉撒的减肥阿斯兰的减肥撒多了几分拉四大皆空弗拉速度快</td>
					<td>3发动机奥斯卡了房间爱施蒂利克减肥拉撒的减肥裸嫁时代佛一而且我刃甲金丝豆腐普</td>
					<td>4发个ID哦啊圣诞节佛奥四等奖佛拉斯递交佛牌一及啊山东腹肌啊山东减肥哦啊死杜甫哦配速金佛我爱神的箭佛is的见覅哦我去哦一啊山东矿机覅我嗯UR啊山东腹肌撒旦
					</td>
					<td>5发个ID哦啊圣诞节佛奥四等奖佛拉斯递交佛牌一及啊山东腹肌啊山东减肥哦啊死杜甫哦配速金佛我爱神的箭佛is的见覅哦我去哦一啊山东矿机覅我嗯UR啊山东腹肌撒旦
					</td>
					<td>6发个ID哦啊圣诞节佛奥四等奖佛拉斯递交佛牌一及啊山东腹肌啊山东减肥哦啊死杜甫哦配速金佛我爱神的箭佛is的见覅哦我去哦一啊山东矿机覅我嗯UR啊山东腹肌撒旦
					</td>
				</tr> -->

			</tbody>
		</table>
		<%
			}
			}
		%>

	</div>
</body>
</html>