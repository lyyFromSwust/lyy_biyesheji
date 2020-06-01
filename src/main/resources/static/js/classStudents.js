
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