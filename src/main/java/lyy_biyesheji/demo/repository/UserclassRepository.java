package lyy_biyesheji.demo.repository;

import lyy_biyesheji.demo.entity.UploadFile;
import lyy_biyesheji.demo.entity.User;
import lyy_biyesheji.demo.entity.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserclassRepository extends JpaRepository<UserClass,Integer> {

    /*  通过班级id查询 此班级用户(老师不包含在内) */
    @Query(value = "select * from t_userclass,t_user where t_userclass.uc_classid=?1 and " +
            "t_userclass.uc_userid==t_user.u_id",nativeQuery = true)
    List<User> findByUc_classid(Integer classid);

}
