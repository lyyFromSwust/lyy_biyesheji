package lyy_biyesheji.demo.repository;

import lyy_biyesheji.demo.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {

    /*  通过问题id查询出所有回答  */
    @Query(value="select * from t_answer ta where ta.a_questionid=?1",nativeQuery = true)
    List<Answer>findByA_questionid(Integer questionid);

}
