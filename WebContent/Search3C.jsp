<%@page import="java.util.logging.Logger"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONException"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.b1b.tc.checker.mgr.DataPro"%>
<%@page import="com.b1b.tc.checker.mgr.DataInterface"%>
<%@page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>3C禁运查询结果</title>
<script type="text/javascript" src="./js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="./js/cookie_utils.js"></script>

<link rel="stylesheet" href="./css/searchbar.css" type="text/css" />
<link rel="stylesheet" href="./css/table.css" type="text/css" />
<link rel="stylesheet" href="./css/tanchaung.css" type="text/css" />
<script type="text/javascript">
	function EnterPress(e) { //传入 event   
		var e = e || window.event;
		if (e.keyCode == 13) {
			document.getElementById("header-search-button").click();
		}
	}

	function detailSearch(id, partno, type) {
		var divTanCt = document.getElementById("dialog_background");
		var html = "";
		var query = "?id=" + encodeURIComponent(id);
		query += "&type=" + encodeURIComponent(type);
		$
				.ajax({
					type : "GET",
					url : "./GetDetailInfoServlet" + query,
					dataType : "json",
					success : function(data) {
						//var detailMx=data;
						try {
							var code = data.code;
							html += "	<div id='div1' class='content'>";
							html += "<div id='close'>";
							html += "	<span id='close-button'>×</span>";
							html += "	<h2>" + partno + " 详细信息</h2>";
							html += "	</div>";
							if (code == 1) {
								var innerJson = data.data;
								if ("3C" == type) {
									/* 	console.log('titles='+title);
										console.log('content='+content);
									 */
									var objItem = innerJson[0];
									var titleStr = objItem.表头;
									titleStr = titleStr.replace(/\r\n/g, "");
									var contentStr = objItem.明细信息;
									contentStr = contentStr
											.replace(/\r\n/g, "");
									var title = eval('(' + titleStr + ')');
									var content = eval('(' + contentStr + ')');

									var temps = "";
									var k = 0;
									temps += "<table style=\"float: left;margin:10px;\">";
									for (var i = 0; i < title.length; i++) {
										var tkey = title[i];
										var tvalue = "-";
										if (i < content.length) {
											tvalue = content[i];
										} else {
											console
													.log("key length>value length");
										}
										if (tkey == '比较零件'
												|| tkey == 'Digi-Key 零件编号'
												|| tkey == '制造商零件编号'
												|| tkey == '现有数量' || tkey == ''
												|| tkey == '描述'
												|| tkey == '最低订购数量'
												|| tkey == '系列'
												|| tkey == '单价USD'
												|| tkey == '制造商'
												|| tkey == '图像'
												|| tkey == '明细页地址'
												|| tkey == '价格区间') {
											continue;
										}
										temps += "<tr>";
										temps += "<td style=\"border: 1px solid rgb(55, 31, 56);padding: 3px;\">"
										temps += tkey;
										temps += "</td>"
										temps += "<td>"
										temps += tvalue;
										temps += "</td>";
										temps += "</tr>";
										k++;
										if (k % 3 == 0) {
											temps += "</table><table style=\"float: left;margin:10px;\">";
										}
									}
									var a = temps.lastIndexOf("</table>");
									if (a + 5 != temps.lenth) {
										temps += "</table>"
									}
									html += temps;

								} else {
									//禁运
									var innerJson = data.data;
									var objItem = innerJson[0];
									var temps = "";
									var k = 0;
									temps += "<table style=\"float: left;margin:10px;\">";
									for ( var key in objItem) {
										if (key == '比较零件'
												|| key == 'Digi-Key零件编号'
												|| key == '制造商零件编号'
												|| key == '现有数量' || key == ''
												|| key == '描述'
												|| key == '最低订购数量'
												|| key == '系列'
												|| key == '单价USD'
												|| key == '制造商' || key == '图像') {
											continue;
										}
										temps += "<tr>";
										temps += "<td style=\"border: 1px solid rgb(55, 31, 56);padding: 3px;\">"
										temps += key
										temps += "</td>"
										temps += "<td>"
										temps += objItem[key]
										temps += "</td>";
										temps += "</tr>";
										k++
										if (k % 3 == 0) {
											temps += "</table><table style=\"float: left;margin:10px;\">"
										}
									}
									var a = temps.lastIndexOf("</table>");
									if (a + 5 != temps.lenth) {
										temps += "</table>"
									}
									html += temps;
								}
							} else {
								var content = data.data;
								html += "	<div id='div2'>";
								html += "	<h3>错误</h3>";

								html += "	<p>查询详情出错," + content + "</p>";
								html += "	</div>:";
								/* 	
									html += "	<h3 id='foot'>底部内容</h3>";
									html += "	</div>"; */
							}
							html += "</div>";
							$("#dialog_background").html("");
							$("#dialog_background").html(html);
							show();
							var close = document.getElementById('close-button');
							close.onclick = closeBtn;
						} catch (e) {
							alert("结果解析异常！！！！,"+e);
							console.log("response error=" + e);
						}
					},
					error : function(resp, code, msg) {
						alert("查询失败，网络异常！！！！,"+msg);
					/* 	$("#tv_alert").html("连接服务器失败");
						fadeIn(iBase.Id('tv_alert'), 500, 100); */
					}
				});
	}
	function onTcShow() {

	}
	function onTcCancel() {

	}
					
	function addCancel(){
		$(".addNewData").css("display","none");
		$(".addNewData input[type='text']").val("");
		/* $("#type").val(""); */
		$("#PartNo").val("");
		$("#dtype").val("");
		$("#mType").val("");
	}
	function addOk(){
		
	/* 	<label>型号</label> <input name="mType" id="PartNo" placeholder="型号" />
			<label>类型</label> <input name="dtype" id="dtype" placeholder="明细型" />
			<label>大类</label> <input name="mType" id="mType" placeholder="大类" /> */
		var iPartno=$("#PartNo").val();
		var iDtype=$("#dtype").val();
		var imType=$("#mType").val();
		var uid=$("#login_id").html();
		var uname=$("#login_name").html();
		var selObj=document.getElementById("type");
		var selIndex=selObj.selectedIndex;
		var type=selObj.options[selIndex].text; 
		var query="?";
		query+="PartNo="+encodeURIComponent(iPartno);
		query+="&dtype="+encodeURIComponent(imType);
		query+="&mtype="+encodeURIComponent(iDtype );
		query+="&type="+encodeURIComponent(type);
		query+="&uid="+encodeURIComponent(uid);
		query+="&uname="+encodeURIComponent(uname);
		$.ajax({
			url:"./AddServlet"+query,
			type:"get",
			dataType:"json",
			success:function(data){
				try{
					if(1==data.code){
						alert("---------添加新数据OK---------");
						addCancel();
					}else{
						alert("添加新数据失败,"+data);
					}
				}catch(e){
					alert("添加新数据失败,"+data);
				}
				console.log("add Result="+data);
			},
			error : function(resp, code, msg) {
				alert("添加新数据失败,"+msg);
			}
		});
	}
	function showAdd(){
		var uid=$("#login_id").html();
		var uname=$("#login_name").html();
		if(uid==''||uname==''){
		alert("请先登录！！！");
		window.location.href="./login.html";
		}else{
			$(".addNewData").css("display","block");
		}
	}
	function exitLogin(){
		delCookie("uid");
		delCookie("uname");
		window.location.href="./login.html";
	}
</script>
<style type="text/css">
#container {
	width: 980px;
	/* margin: 0 auto; */
	margin: 0 20px;
	padding: 0 5px;
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

#caseBlanche #load {
	color: #fff;
	font-family: calibri;
	text-align: center;
	position: absolute;
	top: 42px;
	left: 42px;
}

#id_tb_price td {
	height: 20px;
	width: 60px;
	border: 0;
	background: inherit;
}

#id_tb_price .tb_price_fd td {
	height: 20px;
	width: 60px;
	border: 0;
	background: inherit;
}

#id_tb_price .tb_price_sprice td {
	text-align: left;
	border: 0;
	background: inherit;
}

#id_tb_price .tb_price_total  td {
	margin-left: 10px;
	border: 0;
	background: inherit;
	width: 80px;
	text-align: right;
}

td .tb_price_fd {
	border-radius: 0 0 0 0;
}

#login_panel {
	z-index: 1;
	position: absolute;
	top: 20px;
	left: 800px;
}

#login_panel span {
	color: #f100ff;
}

.addNewData {
	margin: 20px 0 0 0;
	display: none;
}
</style>
</head>

<body>

	<div id="container">
		<h1>3C禁运查询</h1>
		<div style="margin: 50px 0px 0px 0px;">
			<div id="search_containner">
				<form action="./Search3C.jsp">
					<div>
						<div id="serarch_div_btnSearch">
							<button id="header-search-button" type="submit">搜索</button>
						</div>
						<div id="serarch_div_input">
							<%
								String lastparno = request.getParameter("partno");
								if (lastparno == null) {
									lastparno = "";
								}
							%>
							<input id="header-search" placeholder="请输入型号" type="text"
								onkeypress="EnterPress(event)" class="dkdirchanger ac_input"
								name="partno" style="direction: ltr;" value="<%=lastparno%>" />
						</div>
					</div>
				</form>
			</div>
			<div id="tv_alert"></div>
		</div>
		<div id="login_panel">
			登录人：
			<%
			Cookie[] cks = request.getCookies();
			String uid = "";
			String uname = "";
			if(cks!=null){
				for (Cookie c : cks) {
					if (c.getName().equals("uid")) {
						uid = c.getValue();
					} else if (c.getName().equals("uname")) {
						uname = c.getValue();
					}
				}
			}
			if (uid.equals("") || uname.equals("")) {
		%>
			<script>
		/* alert("请先登录！！！");
		window.location.href="./login.html"; */
		</script>
		<span id="login_id"><%=uid%></span>&nbsp; &nbsp;姓名：<span
				id="login_name"><%=uname%></span>
			<%
				} else {
			%>
			<!-- $("#login_id").html(''); -->
			<span id="login_id"><%=uid%></span>&nbsp; &nbsp;姓名：<span
				id="login_name"><%=uname%></span>&nbsp; &nbsp;<a
				href="javascript:exitLogin();">退出登录</a>
			<%
				}
			%>
		</div>
		<div class="3c_toolbar">
			<a href="javascript:showAdd()">新增</a>
		</div>
		<!-- 	String dType, String mType, String PartNo,String type  -->
		<div class="addNewData">
			<label>型号</label> <input name="PartNo" id="PartNo" placeholder="型号" />
			<label>明细类</label> <input name="dtype" id="dtype" placeholder="明细型" />
			<label>大类</label> <input name="mType" id="mType" placeholder="大类" />
			<select id="type">
				<option value="0">3C</option>
				<option value="1">禁运</option>
			</select> <input type="button" value="确认" onclick="addOk();" /> <input
				type="button" value="取消" onclick="addCancel();" />
		</div>
		<div style="margin: 20px;">
			<table id="content_colorful">
				<thead id="table_thead">
					<tr>
						<th class="digikey_heaher">型号</th>
						<th class="digikey_heaher">大类</th>
						<th class="digikey_heaher">明细类</th>
						<th class="digikey_heaher">类型</th>
						<th class="digikey_heaher">详细</th>
						<th class="digikey_heaher">来源</th>
					</tr>

				</thead>
				<tbody>
					<%
						String partno = lastparno;
						DataInterface pro = new DataPro();
						String res = pro.getData(partno);
						/* if ("查询条件不能为空".equals(res)) {
							return;
						} */
						Logger jspLogger = Logger.getLogger("searchLogger");
						try {
							JSONObject object = new JSONObject(res);
							int code = object.getInt("code");
							if (code == 1) {
								String digiid = "";
								String id = "";
								String realName = "";
								String availableCount = "";
								String status = "";
								String minLimit = "";
								String cny = "";
								String usd = "";
								String breakPrice = "";
								String serial = "";
								String producer = "";
								String description = "";
								String updateTime = "";
								String part_type = "";
								String detail_type = "";
								String from = "";
								JSONArray arr = object.getJSONArray("data");
								for (int i = 0; i < arr.length(); i++) {
									try {
										JSONObject temp = arr.getJSONObject(i);
										realName = temp.getString("型号");
										detail_type = temp.getString("明细类");
										part_type = temp.getString("大类");
										status = temp.getString("类型");
										id = temp.getString("id");
										from = temp.getString("来源");
					%>
					<tr class="item">
						<td><%=realName%></td>
						<td><%=part_type%></td>
						<td><%=detail_type%></td>
						<td><%=status%></td>
						<td><button id="open_btn"
								style="text-decoration: none; border: 1px solid rgba(0, 0, 0, 0.1); background: white; color: rgb(0, 14, 206)"
								onclick="detailSearch('<%=id%>','<%=realName%>','<%=status%>');">详情</button></td>
						<td><%=from%></td>
						<%
							} catch (JSONException e) {
											jspLogger.warning("error:" + e.getMessage());
										}
									}
						%>
					</tr>
					<%
						} else {
								String msg = object.getString("data");
					%>
					<script type="text/javascript">
						alert('<%=msg%>');
					</script>
					<%
						}
						} catch (Exception e) {
							e.printStackTrace();
							jspLogger.warning("Response Json Error,\n" + res);
					%>
					<script type="text/javascript">
						alert('查询错误,' +'<%=res%>');
					</script>
					<%
						}
					%>
				</tbody>
			</table>
		</div>

		<div id="dialog_background" class="back"></div>
		<script type="text/javascript" src="./js/tanchaung.js"></script>
	</div>
</body>
</html>