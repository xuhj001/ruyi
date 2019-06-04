function createQianShouDan(id){
	$("#tabs").tabs('close','创建签收单');
	openTab("创建签收单","/repair/createQianShouDan.do?id="+id,"");
}