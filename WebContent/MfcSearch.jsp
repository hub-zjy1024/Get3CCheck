<%@page import="org.json.JSONArray"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="org.json.JSONException"%>
<%@page import="org.json.JSONObject"%>
<%@page import="zjy.wxscan.login.utils.MFCMgr"%>
<%@page import="java.io.Writer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>我的品牌</title>
<script type="text/javascript" src="./js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	function cancelEdit(index) {
		$(".data_editor:eq(" + index + ")").css("display", "none");
	}
	function edit(index,tempId,temptime) {
		var checkId = "";
		var checkTime = "";
		var parent=$(".data_editor:eq(" + index + ")");
		parent.css("display", "block");
		parent.find('[id=m_uid]').val(tempId);
		parent.find('[id=m_time]').val(temptime);
	}
	function fcOk(index,brandId) {
		var checkId = "";
		var checkTime = "";
		var parent=$(".data_editor:eq(" + index + ")");
	//	checkId=parent.children('[id=m_uid]').val();
		//checkTime=parent.children('[id=m_time]').val();
		checkId=parent.find('[id=m_uid]').val();
		checkTime=parent.find('[id=m_time]').val();
	//	alert("uid="+checkId+"&nbsp;&nbsp;checkTime="+checkTime+"&nbsp;&nbsp;brandId="+brandId);
		//$(".data_editor:eq(" + id + ")").css("display", "block");
		//	$()[id].css("display", "block");
		commitModify(brandId,checkId,checkTime);
	}

	function commitModify(id, checkId, checkTime) {
		var mUrl = "./DataController?";
		mUrl += "checkID=" + checkId;
		mUrl += "&id=" + id;
		mUrl += "&checkTime=" + checkTime;
		$.ajax({
			url : mUrl,
			type : "get",
			success : function(res) {
				console.log(id + " updateRes=" + res);
				if ("1" == res) {
					alert("更新成功");
					window.location.reload()
				} else {
					alert("更新失败");
				}
			},
			error : function(p1, p2, p2) {
				alert("更新失败,网络错误，" + p1);
			}
		});

	}
</script>
<style type="text/css">
.containner {
	margin: 0 10px;
}

table {
	text-align: left;
	font-size: 25px;
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
</style>
</head>

<body>
	<!-- {"表":[{"ID":"1436","Name":"TES","CheckID":"101","CheckTime":"10"}] } -->
	<div class="containner">
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
			//response.setContentType("text/html; charset=utf-8");
			response.setCharacterEncoding("utf-8");
			Writer writer = response.getWriter();
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
		<table>
			<thead>
				<tr>
					<th>id</th>
					<th>名称</th>
					<th>审核人</th>
					<th>审核时间</th>
					<th>编辑</th>
				</tr>
			</thead>

			<%
				//writer.append("<p style=\"font-size:20px\">当前登录id=" + wxId + "</p>");
					MFCMgr mgr = new MFCMgr();
					String pinpai = mgr.getPinpai(wxId);
					jspLogg.warning("pingpai:" + pinpai);
					if (pinpai == null) {

					} else {
						try {
							JSONObject dobj = new JSONObject(pinpai);
							JSONArray marray = dobj.getJSONArray("表");
							for (int i = 0; i < marray.length(); i++) {
								JSONObject tempObj = marray.getJSONObject(i);
								String id = tempObj.getString("ID");
								String Name = tempObj.getString("Name");
								String CheckID = tempObj.getString("CheckID");
								String CheckTime = tempObj.getString("CheckTime");
			%>

			<%-- 		<ul>
				<li>id:<%=id%></li>
				<li>名称:<%=Name%></li>
				<li>审核人:<%=CheckID%></li>
				<li>审核时间:<%=CheckTime%></li>
			</ul>
			<div>
				<a href="javascript:edit()">编辑</a>
			</div> --%>
			<tr>

				<td><%=id%></td>
				<td><%=Name%></td>
				<td><%=CheckID%></td>
				<td><%=CheckTime%></td>
				<td><a
					href="javascript:edit(<%=i%>,'<%=CheckID%>','<%=CheckTime%>')">编辑</a>
					<div class="data_editor_wrapper">
						<div class="data_editor">
							<p>待修改</p>
							<!-- 			名称：<input id="m_name" placeholder="名称"></input> -->
							审核人id：<input id="m_uid" placeholder="审核人"></input>
							<div>
								待审核时间(分)：<input id="m_time"
									oninput="value=value.replace(/[^\d]/g,'')"
									placeholder="待审核时间(分)">
							</div>
							<div>
								<input id="m_ok" type="button" value="完成"
									onclick="fcOk(<%=i%>,'<%=id%>')"> <input id="m_cancel"
									type="button" onclick="cancelEdit(<%=i%>)" value="取消">
							</div>
						</div>
					</div></td>
			</tr>
			<%
				}
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
			%>
		</table>
		<%
			}
		%>
	</div>
</body>
</html>