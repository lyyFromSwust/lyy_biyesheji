
/*创建班级窗口*/
var buildclassWindow=document.getElementById("buildClassWindow")
var maskeWindow=document.getElementById("maskeWindow")
function clickToBuildClass() {
	showBuildWindow();
}

function hideBuildWindow(){
	maskeWindow.style.visibility="hidden";
	buildclassWindow.style.transform="scale(0)";

}

function showBuildWindow(){
	maskeWindow.style.visibility="visible";
	buildclassWindow.style.transform="scale(1)";
}

/* 跳转至班级信息 */
function classInfoClick(c_id) {
    console.log(c_id);
    window.location.href="classInfo/"+c_id;
}
/* 跳转至班级学生 */
function classStudentClick(c_id) {
	window.location.href="classStudent/"+c_id;
}
/*  跳转至班级作业  */
function classHomeworkListClick(c_id) {
    window.location.href="classHomeworkList/"+c_id;
}
/* 班级文件 */
function classFileClick(c_id) {
    window.location.href="classFile/"+c_id;
}
/* 留言 */
function classMessageClick(c_id) {
    window.location.href="classMessage/"+c_id;
}
/* 问答 */
function classQandAClick(c_id) {
	window.location.href="classMessage/"+c_id;
}

/*以下为页面管理*/
var firstPage=document.getElementById("firstPage");
var previousPage=document.getElementById("previousPage");
var nextPage=document.getElementById("nextPage");
var lastPage=document.getElementById("lastPage");
var nowPage=document.getElementById("nowPage");
var allPage=document.getElementById("allPage");
(function initPage(){
	console.log(nowPage.innerHTML);
	console.log(allPage.innerHTML);
	
	if(nowPage.innerHTML==allPage.innerHTML){
		nextPage.style.visibility="hidden"
		lastPage.style.visibility="hidden"
	}
	
	if(nowPage.innerHTML==1){
		console.log(firstPage.style)
		firstPage.style.visibility="hidden"
		previousPage.style.visibility="hidden"
	}
})()

function firstClick(){
	window.location.href=window.location.pathname+"?nowpage=1";
}

function lastClick(){
	window.location.href=window.location.pathname+"?nowpage="+allPage.innerHTML;
}

function previousClick(){
	window.location.href=window.location.pathname+"?nowpage="+(Number(nowPage.innerHTML)-1);
}

function nextClick(){
	window.location.href=window.location.pathname+"?nowpage="+(Number(nowPage.innerHTML)+1);
}