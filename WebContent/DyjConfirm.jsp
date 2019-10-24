<%@page import="jdk.nashorn.internal.ir.Flags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>微信授权</title>
</head>
<script type="text/javascript">
	//window.history.forward(1);  
	function closePageForm() {
		//  window.opener=null;
		/*  window.open('','_self');
		 window.close(); */
		//open(location.href, '_self').close();
		window.history.back();

	}
</script>
<style type="text/css">
form>div {
	text-align: center;
}

.btn_auth {
	width: 100px;
	height: 40px;
	font-size: 20px;
	display: block;
	margin: auto;
	color: green;
	border: 1px solid green;
	background: white;
	border-radius: 3px;
}

input.btn_auth:ACTIVE {
	color: white;
	background: #FF5722;
	border: 1px solid black;
}
/* div>input {
	width: 100px;
	height: 40px;
	font-size: 20px;
	display: block;
	margin: auto;
	color: green;
	border: 1px solid green;
	background: white;
	border-radius: 3px;
}
div>input:ACTIVE {
color:white;
	background: #FF5722;
	border: 1px solid white;
} */
[hidden='hidden'] {
	display: none;
}

div>p {
	font-size: 18px;
	height: 100px;
}

.logo {
	background: #a1ce5b;
	width: 200px;
}

.divider_line {
	background: #d8d8d8;
	height: 1px;
	margin: 0px 50px;
}

ul.auth_list {
	font-size: 19px;
}

.auth_list>li {
	font-size: 18px;
	color: #7b7b7b;
}

ul {
	width: 279px;
	margin: 10px auto;
}

#dyjconfirm_cancel {
	color: #7b7b7b;
	border: 1px solid #7b7b7b;
}

iframe {
	border-style: none;
	height: 300px;
}
h2{
color:#3cb371;}
h3{
  color: gray;}

</style>
<body>
	<div class="containner">
		<form action="./LoginChecker">
			<div>
				<%
					/* "/DyjConfirm.jsp?uid=" + erCode
					+ "&qrId=" + URLEncoder.encode(msg, "utf-8") + "&flag="
					*/
					String from = request.getParameter("flag");
					String qrId = request.getParameter("qrId");
					String uid = request.getParameter("uid");
					String iframeUrl = request.getParameter("show_url");
				%>
				<!--  <image src="https://res.wx.qq.com/wxopenres/htmledition/images/favicon32f740.ico" class="img" mode="aspectFill"></image>
				 -->
				<!-- 	<img class="logo"
					src="https://res.wx.qq.com/a/wx_fed/weixin_portal/res/static/img/dNEBuK6.png"
					alt="微信"> -->
				<!-- <iframe src="https://www.baidu.com">
				 </iframe> -->
				<%-- <iframe src="<%=iframeUrl==null?"":iframeUrl%>">
				 </iframe> --%>
				 <%if(iframeUrl==null){
					 %>
					 <img class="logo"
					src="https://res.wx.qq.com/a/wx_fed/weixin_portal/res/static/img/dNEBuK6.png"
					alt="微信">
					 <%
				 }else{
					 %>
				<iframe src="<%=iframeUrl%>">
				 </iframe> 
					 <%
				 } %>
			<!-- 	<iframe
					src="https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=4101130699,1213571795&fm=58">
				</iframe> -->
					<h2>
						<%=from%>
				</h2>
				<h3>
				申请获取以下信息
				</h3>
				<div class="divider_line"></div>
				<ul class="auth_list">
					<li>获取你的公开信息（昵称、头像等）
				</ul>
				<input class="btn_auth" id="dyjconfirm_ok" name="ok" type="submit"
					value="确认"> <br /> <input class="btn_auth"
					id="dyjconfirm_cancel" name="cancel" type="button" value="取消"
					onclick="javascript:closePageForm();"> <input
					id="dyjconfirm_flag" name="flag" hidden="hidden" value="<%=from%>">
				<input id="dyjconfirm_qrId" name="qrId" hidden="hidden"
					value="<%=qrId%>"> <input id="dyjconfirm_uid" name="uid"
					hidden="hidden" value="<%=uid%>"> <input
					id="dyjconfirm_uid" name="tag" hidden="hidden" value="confirm">
				<!-- 			String code = request.getParameter("errorCode");
			String msg = request.getParameter("msg"); -->
			</div>
		</form>
	</div>
</body>
</html>