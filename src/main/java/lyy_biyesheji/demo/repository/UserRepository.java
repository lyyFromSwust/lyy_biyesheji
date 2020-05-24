package lyy_biyesheji.demo.repository;


import lyy_biyesheji.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/*JpaRepository */
public interface UserRepository  extends JpaRepository<User, Integer> {

    /* 登陆查询 通过学号，密码，身份查询*/
    @Query(value = "select * from t_user u where u.u_num=?1 and u.u_password=?2 and u.u_identity=?3",nativeQuery = true)
    List<User> findByU_numAndAndU_passwordAndAndU_identity(String u_num,String u_password,Boolean u_identity);

    /* 注册查询 通过学号，身份查询 */
    @Query(value = "select * from t_user u where u.u_num=?1 and u.u_identity=?2",nativeQuery = true)
    List<User> findByU_numAndAndU_identity(String u_num,Boolean u_identity);

}

