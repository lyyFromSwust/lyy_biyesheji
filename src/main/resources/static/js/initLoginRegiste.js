function changeToLogin(){
	window.location.href="login";
}

function changeToRegiste(){
	window.location.href="registe";
}

(function(){
	if(window.top ==window.self){
		//非内嵌iframe
		
		//增加背景图片
		var fdiv =document.getElementById("imgBg");
		if(fdiv != null){
			var ndiv = document.createElement("div");
			ndiv.innerHTML="<img class=\"bgImg3\"/>";
			fdiv.appendChild(ndiv);
		}
		
		//跳转
		var turnToLogin=document.getElementById("turnToLogin");
		var turnToRegiste=document.getElementById("turnToRegiste");
		if(turnToLogin != null){
			turnToLogin.onclick = function(){changeToLogin();}
		}
		if(turnToRegiste != null){
			turnToRegiste.onclick = function(){changeToRegiste();}
		}
	}
})()