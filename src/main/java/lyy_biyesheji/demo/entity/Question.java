package lyy_biyesheji.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_question")
public class Question {
    @Id
    @GeneratedValue
    private int q_id;

    /*  用户id 代表发送者 */
    @Column(nullable = false)
    private int q_userid;

    /*  班级id 发送班级 */
    @Column(nullable = false)
    private int q_classid;

    /*  问题文本  */
    @Column(nullable = false)
    private String q_question;

    /*  问题附件 通过url获取这个文件  */
    @Column
    private String q_questionurl;

    /*  发送时间  */
    @Column
    private Date q_sendtime;

    public Question() {
    }

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    public int getQ_userid() {
        return q_userid;
    }

    public void setQ_userid(int q_userid) {
        this.q_userid = q_userid;
    }

    public int getQ_classid() {
        return q_classid;
    }

    public void setQ_classid(int q_classid) {
        this.q_classid = q_classid;
    }

    public String getQ_question() {
        return q_question;
    }

    public void setQ_question(String q_question) {
        this.q_question = q_question;
    }

    public String getQ_questionurl() {
        return q_questionurl;
    }

    public void setQ_questionurl(String q_questionurl) {
        this.q_questionurl = q_questionurl;
    }

    public Date getQ_sendtime() {
        return q_sendtime;
    }

    public void setQ_sendtime(Date q_sendtime) {
        this.q_sendtime = q_sendtime;
    }

}
