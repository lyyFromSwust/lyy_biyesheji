var sendMsg=document.getElementById("send_inputMsg");
function sendMsg_click() {
    if(sendMsg.value){
        alert('留言成功');
        return true;
    }
    return false;

}

/*以下为页面管理*/
var firstPage=document.getElementById("firstPage");
var previousPage=document.getElementById("previousPage");
var nextPage=document.getElementById("nextPage");
var lastPage=document.getElementById("lastPage");
var nowPage=document.getElementById("nowPage");
var allPage=document.getElementById("allPage");
(function initPage(){
    sendMsg.value="";

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
function getUrlParam(name) {
    // 取得url中?后面的字符
    var query = window.location.search.substring(1);
    // 把参数按&拆分成数组
    var param_arr = query.split("&");
    for (var i = 0; i < param_arr.length; i++) {
        var pair = param_arr[i].split("=");
        if (pair[0] == name) {
            return pair[1];
        }
    }
    return (false);
}

function firstClick(){

	window.location.href=window.location.pathname+"?nowpage=1&c_id="+getUrlParam("c_id");
}

function lastClick(){
	window.location.href=window.location.pathname+"?nowpage="+allPage.innerHTML+"&c_id="+getUrlParam("c_id");
}

function previousClick(){
	window.location.href=window.location.pathname+"?nowpage="+(Number(nowPage.innerHTML)-1)+"&c_id="+getUrlParam("c_id");
}

function nextClick(){
	window.location.href=window.location.pathname+"?nowpage="+(Number(nowPage.innerHTML)+1)+"&c_id="+getUrlParam("c_id");
}
