package lyy_biyesheji.demo.service;

import lyy_biyesheji.demo.entity.Answer;
import lyy_biyesheji.demo.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AnswerServiceImpl {
    @Autowired
    private AnswerRepository answerRepository;

    /* 查询所有回答 */
    public List<Answer>getAnswerList(){
        return answerRepository.findAll();
    }

    /*  通过回答id查询回答 */
    public Answer getAnswer(int id){
        return answerRepository.findById(id).get();
    }

    /*  创建回答 */
    public Answer insertAnswer(Answer answer){
        answer.setA_sendtime(new Date());
        return answerRepository.save(answer);
    }

    /*  删除回答 */
    public boolean deleteAnswer(int id){
        try{
            answerRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*  通过问题id查询出所有回答 */
    public List<Answer>findByA_questionid(int questionid){
        return answerRepository.findByA_questionid(questionid);
    }

}
