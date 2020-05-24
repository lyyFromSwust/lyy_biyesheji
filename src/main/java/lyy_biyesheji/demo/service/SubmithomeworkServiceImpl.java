package lyy_biyesheji.demo.service;

import lyy_biyesheji.demo.entity.SubmitHomework;
import lyy_biyesheji.demo.repository.SubmithomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubmithomeworkServiceImpl {

    @Autowired
    private SubmithomeworkRepository submithomeworkRepository;

    /* 查询所有提交作业 */
    public List<SubmitHomework> getSubmithomeworkList(){
        return submithomeworkRepository.findAll();
    }
    /*  获得单个提交作业----通过id查询 */
    public SubmitHomework getSubmithomework(int id){
        return submithomeworkRepository.findById(id).get();
    }

    /* 添加一个作业 */
    public SubmitHomework insertSubmithomework(SubmitHomework submitHomework){
        submitHomework.setSh_submittime(new Date());
        return submithomeworkRepository.save(submitHomework);
    }

    /*  删除提交作业 */
    public boolean deleteSubmithomework(int id){
        try{
            submithomeworkRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*  通过作业id获取此作业学生提交情况  */
    public List<SubmitHomework>findBySh_assignhomeworkid(int sh_ahid) {
        return submithomeworkRepository.findBySh_assignhomeworkid(sh_ahid);
    }
    /*  通过学生id获取 获取此学生提交作业情况  */
    public List<SubmitHomework>findBySh_userid(int sh_userid) {
        return submithomeworkRepository.findBySh_userid(sh_userid);
    }
    /*  通过学生id 作业id 获取此学生提交这个作业情况  */
    public List<SubmitHomework>findBySh_assignhomeworkidAndAndSh_userid(int sh_ahid,int sh_userid) {
        return submithomeworkRepository.findBySh_assignhomeworkidAndAndSh_userid(sh_ahid,sh_userid);
    }
}
