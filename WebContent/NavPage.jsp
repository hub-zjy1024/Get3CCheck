<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/common.css" type="text/css" />
<title>我的菜单</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<style type="text/css">
a {
	font-size: 25px;
	color: blue;
}

a:VISITED {
	color: blue;
}
</style>
</head>
<body>
	<div class="containner">
		<h1>我的菜单</h1>
		<%
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
		<a href="./MfcSearch.jsp">我的品牌</a> </br> <a href="./CheckPage.jsp">审核</a>
		<%
			}
		%>
	</div>

</body>
</html>