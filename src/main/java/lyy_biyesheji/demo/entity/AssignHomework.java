package lyy_biyesheji.demo.entity;

import javax.persistence.*;
import java.util.Date;

/*  布置作业表  */
@Entity
@Table(name = "t_assignhomework")
public class AssignHomework {
    @Id
    @GeneratedValue
    private int ah_id;

    /*  班级id  */
    @Column(nullable = false)
    private int ah_classid;

    /*  作业开始时间  */
    @Column(nullable = false)
    private Date ah_starttime;

    /*  作业截止时间  */
    @Column(nullable = false)
    private Date ah_endtime;

    /*  作业文本  */
    @Column(nullable = false)
    private String ah_homework;

    /*  作业附件 通过url获取这个文件  */
    @Column
    private String ah_homeworkurl;

    public AssignHomework() {
    }

    public int getAh_id() {
        return ah_id;
    }

    public void setAh_id(int ah_id) {
        this.ah_id = ah_id;
    }

    public int getAh_classid() {
        return ah_classid;
    }

    public void setAh_classid(int ah_classid) {
        this.ah_classid = ah_classid;
    }

    public Date getAh_starttime() {
        return ah_starttime;
    }

    public void setAh_starttime(Date ah_starttime) {
        this.ah_starttime = ah_starttime;
    }

    public Date getAh_endtime() {
        return ah_endtime;
    }

    public void setAh_endtime(Date ah_endtime) {
        this.ah_endtime = ah_endtime;
    }

    public String getAh_homework() {
        return ah_homework;
    }

    public void setAh_homework(String ah_homework) {
        this.ah_homework = ah_homework;
    }

    public String getAh_homeworkurl() {
        return ah_homeworkurl;
    }

    public void setAh_homeworkurl(String ah_homeworkurl) {
        this.ah_homeworkurl = ah_homeworkurl;
    }

    @Override
    public String toString() {
        return "AssignHomework{" +
                "ah_id=" + ah_id +
                ", ah_classid=" + ah_classid +
                ", ah_starttime=" + ah_starttime +
                ", ah_endtime=" + ah_endtime +
                ", ah_homework='" + ah_homework + '\'' +
                ", ah_homeworkurl='" + ah_homeworkurl + '\'' +
                '}';
    }
}
