
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
	window.location.href="../index_visitor/"+1;
}

function lastClick(){
	window.location.href="../index_visitor/"+allPage.innerHTML;
}

function previousClick(){
	window.location.href="../index_visitor/"+(Number(nowPage.innerHTML)-1);
}

function nextClick(){
	window.location.href="../index_visitor/"+(Number(nowPage.innerHTML)+1);
}

function loginClick(){
	window.location.href="/login";
}

function registeClick(){
	window.location.href="/registe";
}
