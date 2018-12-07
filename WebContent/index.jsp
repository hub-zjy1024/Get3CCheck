<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>3C禁运查询</title>
<link rel="stylesheet" href="./css/table.css"></link>
<script type="text/javascript">
	function EnterPress(e) { //传入 event   
		var e = e || window.event;
		if (e.keyCode == 13) {
			document.getElementById("header-search-button").click();
		}
	}
</script>
<style type="text/css">
body {
	background: url("./image/tooplate_body_hp.png") repeat-x;
	background-color: #ececec;
}

#tooplate_footer {
	clear: both;
	width: 100%;
	height: 20px;
	padding: 15px 5px;
	margin: 0 auto;
	color: #666;
	text-align: center;
	background: url(./images/tooplate_footer.jpg);
	background-repeat: repeat-x;
}

/* 
.ps-headerColumn {
	min-width: 60px;
}

#content_colorful {
	border-collapse: collapse;
	margin: 0 auto;
	heigth: 300px;
	overflow: scroll;
	text-align: center;
	overflow: scroll;
	box-shadow: 5px 5px 5px #888888;
}

#content_colorful td, #content_colorful th {
	border: 1px solid #cad9ea;
	color: #666;
	height: 30px;
}

#content_colorful thead th {
	background-color: #CCE8EB;
	border-top: none;
	width: 100px;
}

#content_colorful tr:nth-child(odd) {
	background: #fff;
}

#content_colorful tr:nth-child(even) {
	background: #F5FAFA;
}

#content_colorful th:first-child {
	border-left: none;
	border-top: none;
	border-radius: 6px 0 0 0;
}

#content_colorful th:last-child {
	border-right: none;
	border-top: none;
	border-radius: 0 6px 0 0;
}

#content_colorful tr:last-child td:first-child {
	border-radius: 0 0 0 6px;
}

#content_colorful tr:last-child td :last-child {
	border-radius: 0 0 6px 0;
}

#content_colorful tr td:first-child {
	border-left: none:
}

#content_colorful tr td:last-child {
	border-right: none:
} */
#pagechoice button {
	text-decoration: underline;
	background: #ececec;
}

.layout {
	display: none;
	position: absolute;
	top: 40%;
	left: 40%;
	width: 20%;
	height: 20%;
	z-index: 1001;
	text-align: center;
}

#caseBlanche {
	background-color: #C2C2C2;
	height: 140px;
	width: 150px;
	padding-top: 10px;
	float: left;
	position: relative;
}

#rond {
	height: 100px;
	width: 100px;
	border: 1px solid #fff;
	border-radius: 50%;
	position: absolute;
	top: 20px;
	left: 15px;
	animation: rond 2s infinite;
	-webkit-animation: rond 2s infinite;
}

#tv_alert {
	line-height: 1.5em;
	padding: 10px 5px;
	margin-top: 10px;
	border-top: 4px solid #036;
	background: #dcdcdc;
	text-align: left;
	font-size: 13px;
	border-top: 4px solid #036;
}

#header-search-button {
	/* height: 22px;
	width: 50px;
	border: 0;
	margin: 0;
	padding: 0 15px;
	cursor: pointer;
	border: 1px solid #ccc;
	-moz-border-radius: 0 2px 2px 0;
	border-radius: 0 2px 2px 0;
	 */
	width: 80px;
	height: 40px;
	border: 0;
	color: #fff;
	float: right;
	cursor: pointer;
	background: #8e44ad;
	font-size: 14px;
	border: none;
}

#header-search {
	/* width: 100%;
	border: 0;
	padding: 0 5px;
	height: 22px;
	box-sizing: border-box; */
	outline: 0px none;
	font-size: 14px;
	border: 0;
	font-weight: bold;
	width: 100%;
	height: 40px;
	padding-left: 2px;
	position: relative;
	right: 0;
	top: 0;
}

#serarch_div_btnSearch {
	height: 40px;
	line-height: 40px;
	border: 0;
	background: #8e44ad;
	overflow: hidden;
	position: relative;
	right: 0;
	top: 0;
	float: right;
}

#search_containner {
	margin-top: 20px;
	height: 40px;
	line-height: 40px;
	border: 1px solid #8e44ad;
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
	display: block;
	overflow: hidden;
}

#serarch_div_input {
	height: 40px;
	line-height: 40px;
	overflow: hidden;
	text-align: left;
	position: relative;
	right: 0;
	top: 0;
}

#test {
	height: 10px;
	width: 10px;
	position: absolute;
	background-color: #fff;
	border-radius: 50%;
	top: 0px;
	left: 10px;
}

#container {
	width: 980px;
	/* margin: 0 auto; */
	margin: 0 20px;
	padding: 0 5px;
}

#caseBlanche #load {
	color: #fff;
	font-family: calibri;
	text-align: center;
	position: absolute;
	top: 42px;
	left: 42px;
}
</style>
</head>
<body>
	<div id="container">
		<!--  	<div>
			<ul style="float: left; list-style: none;">
				<li style="float: left;">我关注的</li>
				<li style="float: left;">历史价格曲线</li>
			</ul>
		</div>-->

		<div style="margin: 50px 20px 20px 20px;">
			<div id="search_containner">
				<form action="./Search3C.jsp?">
					<!-- 	onclick="search(0);" -->
					<div id="serarch_div_btnSearch">
						<button id="header-search-button" type="submit">搜索</button>
					</div>
					<div id="serarch_div_input">
						<input id="header-search" placeholder="请输入型号" type="text"
							onkeypress="EnterPress(event)" class="dkdirchanger ac_input"
							name="partno" style="direction: ltr;">
					</div>
				</form>
			</div>
			<div id="tv_alert"></div>
		</div>
		<div style="margin: 20px;">
			<table id="content_colorful">
				<thead id="table_thead">
					<tr>
					<tr>
						<th class="digikey_heaher">型号</th>
						<th class="digikey_heaher">大类</th>
						<th class="digikey_heaher">明细类</th>
						<th class="digikey_heaher">类型</th>
						<th class="digikey_heaher">详细</th>
						<th class="digikey_heaher">来源</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="caseBlanche" style="display: none;">
			<div id="rond">
				<div id="test"></div>
			</div>
			<div id="load">
				<p>loading</p>
			</div>
		</div>
		<div id="tooplate_footer">
			Copyright © 2048 <a href="#">北京远大创新</a>
			<div class="cleaner"></div>
		</div>
	</div>
</body>
</html>