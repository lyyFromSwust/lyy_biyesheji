package lyy_biyesheji.demo.service;

import lyy_biyesheji.demo.entity.User;
import lyy_biyesheji.demo.entity.UserClass;
import lyy_biyesheji.demo.repository.UserclassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserclassServiceImpl {
    @Autowired
    private UserclassRepository userclassRepository;

    /*  查询所有班级用户关系  */
    public List<UserClass>getUserClassList(){
        return userclassRepository.findAll();
    }

    /*  查询单个班级用户关系----通过id查询 */
    public UserClass getUserClass(int id){
        return userclassRepository.findById(id).get();
    }


    /*  创建班级用户关系 */
    public UserClass insertUserClass(UserClass userClass){
        return userclassRepository.save(userClass);
    }


    /*  删除班级用户关系 */
    public boolean deleteUserClass(int id){
        try{
            userclassRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /* 查询一个班级里的学生 */
    public List<UserClass> findByUc_classid(int classid){
        return userclassRepository.findByUc_classid(classid);
    }
    /* 查询一个班级里的学生数量 */
    public int getClassUserNum(int classid){
        return userclassRepository.findByUc_classid(classid).size();
    }
}
