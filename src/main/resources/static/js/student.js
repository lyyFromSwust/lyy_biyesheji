
function clickToMyClass() {
    window.location.href="myClass?nowpage=1"
}

function clickToAllClass(){
    window.location.href="/student/index?nowpage=1"
}

function joinClass(c_id) {
   $.ajax({
        url:"joinClass/"+c_id,
        type:'POST',
        success:function(result){
            alert(result);
        }
   });
//    window.location.href="joinClass/"+c_id;
}

function classInfoClick(c_id) {
    console.log(c_id);
    window.location.href="classInfo?c_id="+c_id;
}

function classStudentInfoClick(c_id) {
    console.log(c_id);
    window.location.href="classStudentInfo?c_id="+c_id;
}

function classHomeworkListClick(c_id) {
    window.location.href="classHomeworkList/"+c_id;
}

function classFileClick(c_id) {
    window.location.href="classFile?c_id="+c_id;
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
	/*切换按钮*/
	if(window.location.href.indexOf("Class") == -1){
		$('#classTypeSelector').children(".classLine_all").show();
		$('#classTypeSelector').children(".classLine_my").hide();
	}else{		
		$('#classTypeSelector').children(".classLine_my").show();
		$('#classTypeSelector').children(".classLine_all").hide();
	}
	
	/*班级标签*/
	for(var i=0;i<$(".inclassTest").length;i++){
		if(window.location.href.indexOf("Class") != -1){
			// if($(".inclassTest")[i].innerHTML=="true"){
			$($(".inclassTest")[i]).parent().addClass("classItemInfo_myclass");
			$($(".inclassTest")[i]).siblings(".classHideMessageBox").hide();
			$($(".inclassTest")[i]).siblings(".addClassBtn").hide();
		}else{
			$($(".inclassTest")[i]).parent().addClass("classItemInfo_allclass");
			$($(".inclassTest")[i]).siblings(".classHideBtnBox").hide();
			$($(".inclassTest")[i]).siblings(".addClassBtn").show();
		}
	}
	
	/*页码*/
	if(nowPage.innerHTML==allPage.innerHTML){
		nextPage.style.visibility="hidden"
		lastPage.style.visibility="hidden"
	}
	if(nowPage.innerHTML==1){
		firstPage.style.visibility="hidden"
		previousPage.style.visibility="hidden"
	}
	
	
})()

function firstClick(){
	window.location.href=window.location.pathname+"?nowpage=1";
}

function lastClick(){
	window.location.href=window.location.pathname+"?nowpage="+allPage;
}

function previousClick(){
	window.location.href=window.location.pathname+"?nowpage="+(Number(nowPage.innerHTML)-1);
}

function nextClick(){
	window.location.href=window.location.pathname+"?nowpage="+(Number(nowPage.innerHTML)+1);
}

function messageClick(){
    window.location.href="message";
}