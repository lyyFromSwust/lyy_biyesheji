package lyy_biyesheji.demo.repository;

import lyy_biyesheji.demo.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {

    /*  通过发问题的用户id获取所有问题  */
    @Query(value = "select * from t_question q where q.q_userid=?1",nativeQuery = true)
    List<Question> findByQ_userid(int q_userid);

    /*  通过班级id获取所有问题  */
    @Query(value = "select * from t_question q where q.q_classid=?1",nativeQuery = true)
    List<Question> findByQ_classid(int m_aimid);

}
