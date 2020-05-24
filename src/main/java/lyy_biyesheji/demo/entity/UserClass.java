package lyy_biyesheji.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_userclass")
public class UserClass {
    @Id
    @GeneratedValue
    private int uc_id;

    /*  用户id */
    @Column(nullable = false)
    private int uc_userid;

    /*  班级id */
    @Column(nullable = false)
    private int uc_classid;
}
