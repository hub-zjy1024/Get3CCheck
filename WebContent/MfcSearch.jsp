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

<script type="text/javascript">
	function edit() {

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
	min-width: 100px;
}

tr td {
	line-height: 50px;
	border-bottom: solid 1px;
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
				<td><a href="javascript:edit()">编辑</a></td>
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