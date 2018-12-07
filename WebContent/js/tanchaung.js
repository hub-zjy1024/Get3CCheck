var bg_div = document.getElementById('dialog_background');
var close = document.getElementById('close-button');

function show() {
	bg_div.style.display = "block";
	onTcShow();
}
var closeBtn=function(){
	bg_div.style.display = "none";
	
	onTcCancel();
}
if(close!=undefined){
	close.onclick =closeBtn;
}

window.onclick = function close(e) {
	if (e.target == bg_div) {
		bg_div.style.display = "none";
	}
}