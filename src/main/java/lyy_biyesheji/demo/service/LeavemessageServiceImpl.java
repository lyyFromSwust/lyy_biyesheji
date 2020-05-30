package lyy_biyesheji.demo.service;

import lyy_biyesheji.demo.entity.LeaveMessage;
import lyy_biyesheji.demo.entity.Message;
import lyy_biyesheji.demo.repository.LeavemessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LeavemessageServiceImpl {
    @Autowired
    private LeavemessageRepository leavemessageRepository;
    /* 查询所有留言 */
    public List<LeaveMessage> getLeavemessageList(){
        return leavemessageRepository.findAll();
    }
    /*  查询单个留言----通过id查询 */
    public LeaveMessage getLeavemessage(int id){
        return leavemessageRepository.findById(id).get();
    }

    /* 添加一条留言 */
    public LeaveMessage insertLeavemessage(LeaveMessage leaveMessage){
        leaveMessage.setL_ltime(new Date());
        return leavemessageRepository.save(leaveMessage);
    }

    /*  删除留言 */
    public boolean deleteLeavemessage(int id){
        try{
            leavemessageRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /* 通过班级id查询班级留言  */
    public List<LeaveMessage> findByL_classid(int classid){
        return leavemessageRepository.findByL_classid(classid);
    }

}
