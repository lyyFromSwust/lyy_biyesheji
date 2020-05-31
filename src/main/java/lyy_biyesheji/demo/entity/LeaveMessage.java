package lyy_biyesheji.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="t_leavemessage")
public class LeaveMessage {
    @Id
    @GeneratedValue
    private int l_id;

    /* 创建留言用户id */
    @Column(nullable = false)
    private int l_userid;

    /*  班级id  */
    @Column(nullable = false)
    private int l_classid;

    /*  留言文本  */
    @Column
    private String l_leavemessage;

    /*  留言回复文本  */
    @Column
    private String l_replymessage;

    /* 留言时间 */
    @Column
    private Date l_ltime;

    /* 回复留言时间 */
    @Column
    private Date l_rtime;

    public LeaveMessage() {
    }
    public LeaveMessage(int l_id,int l_userid, int l_classid,String l_leavemessage,String l_replymessage,Date l_ltime,Date l_rtime) {
        this.l_id=l_id;
        this.l_userid=l_userid;
        this.l_classid=l_classid;
        this.l_leavemessage=l_leavemessage;
        this.l_replymessage=l_replymessage;
        this.l_ltime=l_ltime;
        this.l_rtime=l_rtime;
    }

    public int getL_id() {
        return l_id;
    }

    public void setL_id(int l_id) {
        this.l_id = l_id;
    }

    public int getL_userid() {
        return l_userid;
    }

    public void setL_userid(int l_userid) {
        this.l_userid = l_userid;
    }

    public int getL_classid() {
        return l_classid;
    }

    public void setL_classid(int ah_classid) {
        this.l_classid = ah_classid;
    }

    public String getL_leavemessage() {
        return l_leavemessage;
    }

    public void setL_leavemessage(String l_leavemessage) {
        this.l_leavemessage = l_leavemessage;
    }

    public String getL_replymessage() {
        return l_replymessage;
    }

    public void setL_replymessage(String l_replymessage) {
        this.l_replymessage = l_replymessage;
    }

    public Date getL_ltime() {
        return l_ltime;
    }

    public void setL_ltime(Date l_ltime) {
        this.l_ltime = l_ltime;
    }

    public Date getL_rtime() {
        return l_rtime;
    }

    public void setL_rtime(Date l_rtime) {
        this.l_rtime = l_rtime;
    }

    static public class send_LeaveMessage extends LeaveMessage{
        private String l_username;
        public  send_LeaveMessage(){}
        public  send_LeaveMessage(LeaveMessage leaveMessage,String username){
            super(leaveMessage.l_id,leaveMessage.l_userid,leaveMessage.l_classid,leaveMessage.l_leavemessage,leaveMessage.l_replymessage,leaveMessage.l_ltime,leaveMessage.l_rtime);
            this.l_username=username;
        }

        public String getL_username() {
            return l_username;
        }

        public void setL_username(String l_username) {
            this.l_username = l_username;
        }
    }
}
