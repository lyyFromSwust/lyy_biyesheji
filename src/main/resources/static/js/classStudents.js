
function exportStudent(){

}

var uploadFile_div=document.getElementById("uploadFile");
function importStudent() {
    if(uploadFile_div.style.visibility == "hidden"){
        uploadFile_div.style.visibility = "visible";
    }else
        uploadFile_div.style.visibility = "hidden";
}

var flag=1;
function uploadFile(){
    if(flag==1){
        flag=0;

    }
}

function del_student(u_id,c_id) {
    if(confirm('确认删除该学生吗？')){
        window.location.href=window.location.pathname+"/delete?u_id="+u_id+"&c_id="+c_id;
    }
}