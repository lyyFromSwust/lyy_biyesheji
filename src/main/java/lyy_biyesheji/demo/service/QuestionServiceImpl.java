package lyy_biyesheji.demo.service;

import lyy_biyesheji.demo.entity.Message;
import lyy_biyesheji.demo.entity.Question;
import lyy_biyesheji.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl {
    @Autowired
    private QuestionRepository questionRepository;

    /* 查询所有问题 */
    public List<Question> getQuestionList(){
        return questionRepository.findAll();
    }
    /*  查询单个问题----通过id查询 */
    public Question getQuestion(int id){
        return questionRepository.findById(id).get();
    }

    /* 添加一个问题 */
    public Question insertMessage(Question question){
        question.setQ_sendtime(new Date());
        return questionRepository.save(question);
    }

    /*  删除问题 */
    public boolean deleteQuestion(int id){
        try{
            questionRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*  通过发问题的id查出此人的问题  */
    public List<Question> findByQ_userid(int userid){
        return questionRepository.findByQ_userid(userid);
    }

    /*  用班级id查出此班级问题  */
    public List<Question> findByQ_classid(int classid){
        return questionRepository.findByQ_classid(classid);
    }


}
