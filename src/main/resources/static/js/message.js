(function(){
    var mtypes=$(".mtype");
    var mreads=$(".mread");
    var mresults=$(".mresult");
    var messageDeals=$(".messageDeal");
    var rejectBtns=$(".rejectBtn");
    var acceptBtns=$(".acceptBtn");
    var dealStr=["","未处理","已接受","已拒绝","已忽略"]

    for(var i=0;i<mtypes.length;i++){
        var mtype=$(mtypes[i]);
        var messageDeal=$(messageDeals[i]);
        var rejectBtn=$(rejectBtns[i]);
        var acceptBtn=$(acceptBtns[i]);

        if(mreads[i].innerHTML=="false")mtype.parent().addClass("message_unread");
        else mtype.parent().addClass("message_read");

        if(mtypes[i].innerHTML =="1" || mtypes[i].innerHTML == "2"){
            messageDeals[i].innerHTML=dealStr[Number(mresults[i].innerHTML)];
            if(mresults[i].innerHTML == "1"){
                rejectBtn.show();
                acceptBtn.show();
                messageDeal.hide();
            }else{
                rejectBtn.hide();
                acceptBtn.hide();
                messageDeal.show();
            }
        }else{
                acceptBtn.hide();
                rejectBtn.hide();
                messageDeal.hide();
        }

        if(mtypes[i].innerHTML =="4" || mtypes[i].innerHTML == "5"){
            mtype.parent().addClass("messageItem_canSelect");
        }

    }

    if($(".messageItem").length == 0)
    {
        $("#hitMessage").text("无消息");
    }
})()

function returnClass(){
    window.location.href="index?nowpage=1";
}

function acceptMessage(m_id){
    $(this).attr("onclick","");
    $.ajax({
        url:"messageDeal",
        type:'Post',
        data:{
            messageId:m_id,
            state:'accept'
        },
        success:function(data){
//             alert(data);
            window.location.href="message";
        }
    });
}

function rejectMessage(m_id){
    $(this).attr("onclick","");
    $.ajax({
            url:"messageDeal",
            type:'Post',
           data:{
               messageId:m_id,
               state:'reject'
           },
            success:function(data){
//                alert(data);
                window.location.href="message";
            }
        });
}
