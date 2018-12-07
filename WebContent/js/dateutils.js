
js时间戳转成日期格式
//第一种
function getLocalTime(nS) {
	return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/,
			' ');
}
alert(getLocalTime(1293072805));
// 结果是2010年12月23日 10:53
// 第二种
function getLocalTime(nS) {
	return new Date(parseInt(nS) * 1000).toLocaleString().substr(0, 17)
}
alert(getLocalTime(1293072805));
// 第三种 格式为：2010-10-20 10:00:00
function getLocalTime(nS) {
	return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-")
			.replace(/日/g, " ");
}
alert(getLocalTime(1177824835));