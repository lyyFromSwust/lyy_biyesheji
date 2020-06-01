package lyy_biyesheji.demo.service;

import lyy_biyesheji.demo.entity.UploadFile;
import lyy_biyesheji.demo.repository.UploadfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UploadfileServiceImpl {
    @Autowired
    private UploadfileRepository uploadfileRepository;

    /*  查询所有文件 */
    public List<UploadFile> getFileList(){
        return uploadfileRepository.findAll();
    }

    /*  查询单个文件----通过id查询 */
    public UploadFile getFile(int id){
        System.out.println("id="+id);
        return uploadfileRepository.findById(id).get();
    }


    /*  创建文件 */
    public UploadFile insertFile(UploadFile uploadFile){
        return uploadfileRepository.save(uploadFile);
    }

    /*  删除文件 */
    public boolean deleteFile(int id){
        try{
            uploadfileRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*  通过老师id查询此老师上传文件  */
    public List<UploadFile>findByF_teacherid(int teacherid){
        return uploadfileRepository.findByF_teacherid(teacherid);
    }

    /*  通过老师id 班级查询此老师上传到此班级文件  */
    public List<UploadFile>findByF_teacheridAndAndF_classid(int teacherid,int classid){
        return uploadfileRepository.findByF_teacheridAndAndF_classid(teacherid,classid);
    }

    public List<UploadFile>findByF_classid(int classid){
        return uploadfileRepository.findByF_classid(classid);
    }

}
