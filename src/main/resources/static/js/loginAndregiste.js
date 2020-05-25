var r_div=document.getElementById("registerDiv");
var r_num=document.getElementById("r_num");
var r_name=document.getElementById("r_name");
var r_pwd=document.getElementById("r_password");
var r_pwd_cfm=document.getElementById("r_re_password");
var l_pwd=document.getElementById("l_pwd");
var m_pwd=document.getElementById("m_pwd");


//验证学号 由4-16个只能由字母数字字母组合
function checkNum(id,infoId) {
	var patrn=/^[a-zA-Z0-9]{4,16}$/;
	var txtusername=document.getElementById(id).value;
	if(!patrn.exec(txtusername)){
		setInfo(infoId,"用户名必须为4-16个数字字母组合");
		return false;
	}
	return true;
}

//验证姓名
function checkName(id,infoId) {

	var txtName=document.getElementById(id).value;
	if(txtName==null){
		setInfo(infoId,"姓名不能为空");
		return false;
	}
	return true;
}

//验证密码 由8-16个数字字母组合，且同时含有数字字母
function checkPassword(id,infoId) {
	var patrn=/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;
	var txtpassword=document.getElementById(id).value;
	if(!patrn.exec(txtpassword)){
		setInfo(infoId,"密码必须为8-16个字母数字组合");
		return false
	}
	return true
}

//重复密码
function checkRePassword(pwd1, pwd2, infoId){
	var txtPwd1 = document.getElementById(pwd1).value;
	var txtPwd2 = document.getElementById(pwd2).value;
	if(txtPwd1!= txtPwd2){
		setInfo(infoId, "与密码不完全一致");
		return false;
	}
	var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;
	if(!reg.test(txtPwd2)) {
		setInfo(infoId,'密码必须为8-16个字母数字组合');
		return false;
	}
	return true;
}

//清除信息
function clearInfo(id) {
	if(document.getElementById(id) != null)
	document.getElementById(id).innerText=" ";
}

//设置提示信息
function setInfo(id,info) {
	if(document.getElementById(id) != null)
	document.getElementById(id).innerText=info;
}


//校验所有表单元素的内容
function checkAll() {
	alert("checkAll");
	if(checkNum('r_num','numInfo') && checkName("r_name","nameInfo") && checkPassword('r_password', 'passwordInfo')
		&& checkRePassword('r_password', 'r_re_password', 'rpasswordInfo')){
		r_pwd.value=hex_md5(r_pwd.value);
		return true;
	}
	return false;
}


function checkUAndP(){
	l_pwd.value=hex_md5(l_pwd.value);
	return true;
}


function resetRegister(){
	//重设注册
	r_div.value="";
	r_num.value="";
	r_pwd.value="";
	r_pwd_cfm.value="";
	r_name.value="";
}

function registerWindow(){
	//注册窗口
	window.location.href="registe";
}

function managerLoginSubmit() {
	window.location.href("userList");
}

function checkManager() {
	m_pwd.value=hex_md5(m_pwd.value);
	return true;
}
