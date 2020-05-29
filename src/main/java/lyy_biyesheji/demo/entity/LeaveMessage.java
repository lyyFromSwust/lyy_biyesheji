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


}
