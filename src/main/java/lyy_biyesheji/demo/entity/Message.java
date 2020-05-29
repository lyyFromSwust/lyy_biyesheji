package lyy_biyesheji.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="t_message")
public class Message {

    @Id
    @GeneratedValue
    private int m_id;

    /*  创建用户id  */
    @Column(nullable = false)
    private int m_buildid;

    /*  目标用户id  */
    @Column(nullable = false)
    private int m_aimid;

    /*  班级id  */
    @Column(nullable = false)
    private int m_classid;

    /*  通知消息类型 m_type
    * (1) 学生申请加入通知、
    * (2) 老师邀请加入通知；
    * (3) 加入班级成功通知；
    * (4) 有人留言通知；
	* (5) 有人提问通知、有人回复通知
    * */
    @Column(nullable = false)
    private int m_type;

    /*  是否处理消息  */
    @Column(nullable = false)
    private boolean m_issolved;

    /*  处理结果 1.未处理 2.接受 3.拒绝 4.忽略（234都是处理态度）  */
    @Column(nullable = false)
    private int m_solveresulte;

    /*  是否阅读消息  小红点提示 */
    @Column(nullable = false)
    private boolean m_isread;

    /*  通知消息文本 */
    @Column(nullable = false)
    private String m_message;

    /*  发送时间  */
    @Column
    private Date m_sendtime;

    public Message() {
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public int getM_buildid() {
        return m_buildid;
    }

    public void setM_buildid(int m_buildid) {
        this.m_buildid = m_buildid;
    }

    public int getM_aimid() {
        return m_aimid;
    }

    public void setM_aimid(int m_aimid) {
        this.m_aimid = m_aimid;
    }

    public int getM_classid() {
        return m_classid;
    }

    public void setM_classid(int m_classid) {
        this.m_classid = m_classid;
    }

    public int getM_type() {
        return m_type;
    }

    public void setM_type(int m_type) {
        this.m_type = m_type;
    }

    public boolean isM_issolved() {
        return m_issolved;
    }

    public void setM_issolved(boolean m_issolved) {
        this.m_issolved = m_issolved;
    }

    public int getM_solveresulte() {
        return m_solveresulte;
    }

    public void setM_solveresulte(int m_solveresulte) {
        this.m_solveresulte = m_solveresulte;
    }

    public boolean isM_isread() {
        return m_isread;
    }

    public void setM_isread(boolean m_isread) {
        this.m_isread = m_isread;
    }

    public String isM_message() {
        return m_message;
    }

    public void setM_message(String m_message) {
        this.m_message = m_message;
    }

    public Date getM_sendtime() {
        return m_sendtime;
    }

    public void setM_sendtime(Date m_sendtime) {
        this.m_sendtime = m_sendtime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "m_id=" + m_id +
                ", m_buildid=" + m_buildid +
                ", m_aimid=" + m_aimid +
                ", m_classid=" + m_classid +
                ", m_type=" + m_type +
                ", m_issolved=" + m_issolved +
                ", m_solveresulte=" + m_solveresulte +
                ", m_isread=" + m_isread +
                ", m_message=" + m_message +
                ", m_sendtime=" + m_sendtime +
                '}';
    }
}
