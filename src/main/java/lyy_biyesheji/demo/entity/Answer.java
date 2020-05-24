package lyy_biyesheji.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_answer")
public class Answer {
    @Id
    @GeneratedValue
    private int a_id;

    /*  用户id 代表发送者 */
    @Column(nullable = false)
    private int a_userid;

    /*  问题id 代表回复问题 */
    @Column(nullable = false)
    private int a_questionid;

    /*  回答文本  */
    @Column(nullable = false)
    private String a_answer;

    /*  问题附件 通过url获取这个文件  */
    @Column
    private String a_answerurl;

    /*  发送时间  */
    @Column
    private Date a_sendtime;

    public Answer() {
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public int getA_userid() {
        return a_userid;
    }

    public void setA_userid(int a_userid) {
        this.a_userid = a_userid;
    }

    public int getA_questionid() {
        return a_questionid;
    }

    public void setA_questionid(int a_classid) {
        this.a_questionid = a_classid;
    }

    public String getA_answer() {
        return a_answer;
    }

    public void setA_answer(String a_answer) {
        this.a_answer = a_answer;
    }

    public String getA_answerurl() {
        return a_answerurl;
    }

    public void setA_answerurl(String a_answerurl) {
        this.a_answerurl = a_answerurl;
    }

    public Date getA_sendtime() {
        return a_sendtime;
    }

    public void setA_sendtime(Date a_sendtime) {
        this.a_sendtime = a_sendtime;
    }
}
