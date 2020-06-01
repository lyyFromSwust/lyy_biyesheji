function returnHomeworkList(c_id){
    window.location.href="../classHomeworkList?c_id="+c_id;
}

(function(){
if($(".homeworkListItem").size() != 0)
    $("#hitText").hide();
})()