//写cookies 

function setCookie(name, value) {
	setCookie(name,value,-1);
}
function setCookie(name, value,timeoutDays) {
	var exp = new Date();
	if(timeoutDays==-1||timeoutDays==undefined){
		exp.setTime(exp.getTime() + 60*365 * 24 * 60 * 60 * 1000);
	}else{
		exp.setTime(exp.getTime() + timeoutDays * 24 * 60 * 60 * 1000);
	}
	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString();
}
// 读取cookies
function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

	if (arr = document.cookie.match(reg))

		return unescape(arr[2]);
	else
		return null;
}

// 删除cookies
function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null)
		document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}