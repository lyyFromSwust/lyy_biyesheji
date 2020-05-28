var uploadFile_div=document.getElementById("uploadFile");
function uploadBtn_Click() {
    if(uploadFile_div.style.visibility == "hidden"){
        uploadFile_div.style.visibility = "visible";
    }else
        uploadFile_div.style.visibility = "hidden";
}
