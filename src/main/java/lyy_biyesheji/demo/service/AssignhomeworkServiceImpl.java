package lyy_biyesheji.demo.service;

import lyy_biyesheji.demo.entity.Answer;
import lyy_biyesheji.demo.entity.AssignHomework;
import lyy_biyesheji.demo.repository.AssignhomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AssignhomeworkServiceImpl {

    @Autowired
    private AssignhomeworkRepository assignhomeworkRepository;

    /* 查询所有布置作业 */
    public List<AssignHomework> getAssignhomeworkList(){
        return assignhomeworkRepository.findAll();
    }

    /*  通过id查询布置作业 */
    public AssignHomework getAssignhomework(int id){
        return assignhomeworkRepository.findById(id).get();
    }

    /*  布置作业 */
    public AssignHomework insertAnswer(AssignHomework assignHomework){
        assignHomework.setAh_starttime(new Date());
        return assignhomeworkRepository.save(assignHomework);
    }

    /*  删除回答 */
    public boolean deleteAssignhomework(int id){
        try{
            assignhomeworkRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*  用classid查询出这个班级的作业  */
    public List<AssignHomework> findByAh_classid(int classid){
        return assignhomeworkRepository.findByAh_classid(classid);
    }

}
