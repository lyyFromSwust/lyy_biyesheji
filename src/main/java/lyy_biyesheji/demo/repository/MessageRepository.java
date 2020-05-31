package lyy_biyesheji.demo.repository;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lyy_biyesheji.demo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    /*  通过发送通知的用户id获取所有消息  */
    @Query(value = "select * from t_message m where m.m_buildid=?1",nativeQuery = true)
    List<Message> findByM_buildid(int m_buildid);

    /*  通过接受用户id（目标用户）获取所有消息  */
    @Query(value = "select * from t_message m where m.m_aimid=?1",nativeQuery = true)
    List<Message> findByM_aimid(int m_aimid);

    /*  通过是否阅读消息展示未读消息  */
    @Query(value = "select * from t_message m where m.m_aimid=?1 and m.m_isread=?2",nativeQuery = true)
    List<Message> findByM_aimidAndAndM_isread(int m_aimid,Boolean m_isread);

    /* 学生发出加入班级申请记录查询 */
    @Query(value="select * from t_message m where m.m_buildid=?1 and m.m_classid=?2 and m.m_type=?3",nativeQuery = true)
    List<Message>findByM_buildidAndAndM_classidAndM_type(int buildid,int classid,int type);

    /*  清除用户全部未读  */
    @Query(value = "update t_message m set m.m_isread = 'true' where m.m_aimid = ?1",nativeQuery = true)
    void updateUser_isread(int id);

    /*设置消息内容更新*/
    @Query(value = "update t_message m set m.m_isread = 'true' where m.m_aimid = ?1",nativeQuery = true)
    void updateMessageResult(int userid,int messageId,int dealResult);
}
