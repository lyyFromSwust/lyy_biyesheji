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

    public int getUc_id() {
        return uc_id;
    }

    public void setUc_id(int uc_id) {
        this.uc_id = uc_id;
    }

    public int getUc_userid() {
        return uc_userid;
    }

    public void setUc_userid(int uc_userid) {
        this.uc_userid = uc_userid;
    }

    public int getUc_classid() {
        return uc_classid;
    }

    public void setUc_classid(int uc_classid) {
        this.uc_classid = uc_classid;
    }
}
