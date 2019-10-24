<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/common.css" type="text/css" />
<title>登录结果</title>
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

.error_msg {
	font-size: 25px;
	color: red;
}

.normal_msg {
	font-size: 25px;
}
</style>
</head>
<body>
	<div class="containner">
		<%
			String code = request.getParameter("errorCode");
			String msg = request.getParameter("msg");
			String fromFlag = request.getParameter("flag");
			if ("1".equals(code)) {
		%>
		<h1>成功</h1>
		<p class="normal_msg">
			<%=msg%>
		</p>
		<%
			} else {
		%>
		<h1>错误</h1>
		<p class="error_msg">
			授权'<%=fromFlag %>'登录失败,<%=msg%>
		</p>
		<%
			}
		%>
	</div>
</body>
</html>