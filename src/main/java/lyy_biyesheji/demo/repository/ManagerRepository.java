package lyy_biyesheji.demo.repository;

import lyy_biyesheji.demo.entity.Manager;
import lyy_biyesheji.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    /* 登陆查询 通过用户名，密码*/
    @Query(value = "select * from t_manager m where m.m_name=?1 and m.m_password=?2",nativeQuery = true)
    List<Manager> findByM_nameAndAndM_password(String m_name, String m_password);

}
