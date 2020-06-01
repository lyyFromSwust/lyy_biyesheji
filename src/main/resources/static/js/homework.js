/* 上传作业 */
function sendhomework_click() {
    var ahname=document.getElementById("ahname");
    var ahendtime=document.getElementById("ahendtime");

    if(ahendtime.value&&ahname.value)return true;
    return false;
}

/* 提交作业 */
function submithomework_click() {

    var shtext=document.getElementById("shtext");
    var shfile=document.getElementById("shfile");

    if(shtext.value&&shfile.value)return true;
    return false;

}
/* 跳转到作业详情 */
function homeworkInfo_click(ah_id) {
    window.location.href=window.location.pathname+"/submitHomework?ah_id="+ah_id;
}