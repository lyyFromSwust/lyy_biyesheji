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
    private int ah_classid;

    /*  留言文本  */
    @Column(nullable = false)
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

    public int getAh_classid() {
        return ah_classid;
    }

    public void setAh_classid(int ah_classid) {
        this.ah_classid = ah_classid;
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
}
