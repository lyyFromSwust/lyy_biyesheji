package lyy_biyesheji.demo.repository;

import lyy_biyesheji.demo.entity.LeaveMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeavemessageRepository extends JpaRepository<LeaveMessage, Integer> {

    /*  通过班级id查询出班级所有留言  */
    @Query(value="select * from t_leavemessage tlm where tlm.l_classid=?1",nativeQuery = true)
    List<LeaveMessage> findByL_classid(Integer classid);

}
