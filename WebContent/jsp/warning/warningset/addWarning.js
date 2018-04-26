function oneNext(nowid,oldid){
	
	tabDiv(nowid,oldid);
	
}
function twoNext(nowid,oldid){
	
	tabDiv(nowid,oldid);
	
}
function tabDiv(nowid,oldid){
	$("#"+nowid).show();
	$("#"+oldid).hide();
}