/* 上传作业 */
function sendhomework_click() {
    // var ahname=document.getElementById("ahname");
    // var ahendtime=document.getElementById("ahendtime");
    //
    // if(ahendtime.value&&ahname.value)
    return true;
}

/* 提交作业 */
function submithomework_click() {

    var shtext=document.getElementById("shtext");
    var shfile=document.getElementsByName("fileName")[0];

    if(shtext.value&&shfile.value)return true;
    return false;

}
/* 跳转到作业详情 */
function homeworkInfo_click(ah_id) {
    window.location.href=window.location.pathname+"/submitHomework?ah_id="+ah_id;
}

var flag_hmshow=0;
function clickNewHomework(){
    if(flag_hmshow==0){
        flag_hmshow=1;
        newHomework_show();
    }
    else if(flag_hmshow==1){
        flag_hmshow=0;
        newHomework_hide();
    }
}

function newHomework_show(){
    $("#newHomeworkFrame").addClass("newHomeworkFrame_show");
    $("#newHomeworkFrame").removeClass("newHomeworkFrame_hide");
}

function newHomework_hide(){
    $("#newHomeworkFrame").removeClass("newHomeworkFrame_show");
    $("#newHomeworkFrame").addClass("newHomeworkFrame_hide");
}

(function(){
    if($(".homeworkInfo").length != 0)
        $("#hitText").hide();
})()