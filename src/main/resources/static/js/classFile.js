var uploadFile_div=document.getElementById("uploadFile");
function uploadBtn_Click() {
    if(uploadFile_div.style.visibility == "hidden"){
        uploadFile_div.style.visibility = "visible";
    }else
        uploadFile_div.style.visibility = "hidden";
}


function downloadFile(f_fileurl){
    window.location.href=window.location.pathname+"/download?f_fileurl="+f_fileurl;
}

function del_file(f_id) {
    if(confirm('确认删除该文件吗？')){
        window.location.href=window.location.pathname+"/delete?f_id="+f_id;
    }
}