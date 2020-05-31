package lyy_biyesheji.demo.service;

import lyy_biyesheji.demo.entity.Message;
import lyy_biyesheji.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl {
    @Autowired
    private MessageRepository messageRepository;

    /* 查询所有通知消息 */
    public List<Message> getMessageList(){
        return messageRepository.findAll();
    }
    /*  查询单个用户----通过id查询 */
    public Message getMessage(int id){
        return messageRepository.findById(id).get();
    }

    /* 添加一条消息 */
    public Message insertMessage(Message message){
        message.setM_sendtime(new Date());
        return messageRepository.save(message);
    }

    /*  删除消息 */
    public boolean deleteMessage(int id){
        try{
            messageRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*  查询当前用户所收到的所有消息  */
    public List<Message>findByM_aimid(int aimid){
        return messageRepository.findByM_aimid(aimid);
    }

    /*  查询当前用户发出去的所有消息  */
    public List<Message>findByM_buildid(int m_buildid){
        return messageRepository.findByM_buildid(m_buildid);
    }

    /*  查询当前用户所有未读消息,记得要将m_isread标记为false  */
    public List<Message> findByM_aimidAndAndM_isread(int m_aimid, boolean m_isread){
        return messageRepository.findByM_aimidAndAndM_isread(m_aimid,m_isread);
    }

    /*  计算当前用户未读消息数量  */
    public int getAimidIsreadFalseNum(int m_aimid, boolean m_isread){
        return messageRepository.findByM_aimidAndAndM_isread(m_aimid,m_isread).size();
    }

    /* 查询是否已经向数据库添加了学生申请加入的信息 */
    public List<Message>findByM_buildidAndAndM_classidAndM_type(int buildid,int classid,int type){
        return messageRepository.findByM_buildidAndAndM_classidAndM_type(buildid,classid,type);
    }

    /* 查询是否已经向数据库添加了学生申请加入的信息 */
    public void clearUserRead(int userId){
        messageRepository.updateUser_isread(userId);
    }

    /**/
    public void setDealResult(int userId,int messageId,String dealState){
        int dealStateNum=3;
        if(dealState=="accept")dealStateNum=1;
        if(dealState=="reject")dealStateNum=2;
        messageRepository.updateMessageResult(userId);
    }
}
