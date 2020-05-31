package lyy_biyesheji.demo.service;

import lyy_biyesheji.demo.entity.MClass;
import lyy_biyesheji.demo.entity.Manager;
import lyy_biyesheji.demo.entity.User;
import lyy_biyesheji.demo.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ClassServiceImpl {

    @Autowired
    private ClassRepository classRepository;

    /*  查询所有班级 */
    public List<MClass> getClassList(){
        return classRepository.findAll();
    }

    /*  查询单个班级----通过id查询 */
    public MClass getClass(int id){
        return classRepository.findById(id).get();
    }


    /*  创建班级 */
    public MClass insertClass(MClass mclass){
        mclass.setC_buildtime(new Date());
        return classRepository.save(mclass);
    }

    /*  修改班级 */
    public MClass updateClass(MClass mcalss){
        MClass mcalss1 = classRepository.findById(mcalss.getC_id()).get();
        if(mcalss1==null){
            return null;
        }
        mcalss1.setC_introduce(mcalss.getC_introduce());
        mcalss1.setC_teacherid(mcalss.getC_teacherid());
        return classRepository.save(mcalss1);
    }

    /*  删除班级 */
    public boolean deleteClass(int id){
        try{
            classRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /* 老师页面显示班级查询  */
    public List<MClass> findByC_teacherid(Integer teacherid){
        return classRepository.findByC_teacherid(teacherid);
    }

    /* 老师创建班级查询  */
    public List<MClass> findByC_teacheridAndAndC_classname(Integer teacherid,String classname){
        return classRepository.findByC_teacheridAndAndC_classname(teacherid,classname);
    }

    /*通过关键字模糊搜索*/
    public List<MClass> findByC_classname(String keyname){
        return classRepository.findByC_classname(keyname);
    }
}
