
/*创建班级窗口*/
var buildclassWindow=document.getElementById("buildClassWindow")
function clickToBuildClass() {
    // window.location.href="buildClass"
}

function clickCloseWindow(){
	
}

function clickCloseWindow(){
	
}

/**/
function classInfoClick(c_id) {
    console.log(c_id);
    window.location.href="classInfo/"+c_id;
}

function classHomeworkListClick(c_id) {
    window.location.href="classHomeworkList/"+c_id;
}

function classFileClick(c_id) {
    window.location.href="classFile/"+c_id;
}

function classMessageClick(c_id) {
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
	window.location.href="../index_teacher/1";
}

function lastClick(){
	window.location.href="../index_teacher/"+allPage.innerHTML;
}

function previousClick(){
	window.location.href="../index_teacher/"+(Number(nowPage.innerHTML)-1);
}

function nextClick(){
	window.location.href="../index_teacher/"+(Number(nowPage.innerHTML)+1);
}