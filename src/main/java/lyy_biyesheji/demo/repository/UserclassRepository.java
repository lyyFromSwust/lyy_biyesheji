package lyy_biyesheji.demo.repository;

import lyy_biyesheji.demo.entity.UploadFile;
import lyy_biyesheji.demo.entity.User;
import lyy_biyesheji.demo.entity.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserclassRepository extends JpaRepository<UserClass,Integer> {

    /*  通过班级id查询 此班级用户(老师不包含在内) */
    @Query(value = "select * from t_userclass where t_userclass.uc_classid=?1",nativeQuery = true)
    List<UserClass> findByUc_classid(Integer classid);

    /*  通过用户id查询   */
    @Query(value = "select * from t_userclass where t_userclass.uc_userid=?1",nativeQuery = true)
    List<UserClass> findByUc_userid(Integer userid);

    /*  通过班级id查询，用户id查询是否存在此条记录 不存在即可进行插入 */
    @Query(value = "select * from t_userclass where t_userclass.uc_classid=?1 and t_userclass.uc_userid=?2",nativeQuery = true)
    List<UserClass> findByUc_classidAndAndUc_userid(Integer classid,Integer userid);

}
