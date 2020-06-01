function returnQuestion(c_id){
    window.location.href="classQuestion?nowpage=1&c_id="+c_id;
}
var replyState=0;
function clickReply(){
    if(replyState==0){
        $("#replyForm").removeClass("answerReply_hide");
        $("#replyForm").addClass("answerReply_show");
        replyState=1;
    }else if(replyState==1){
        $("#replyForm").removeClass("answerReply_show");
        $("#replyForm").addClass("answerReply_hide");
        replyState=0;
    }
}