package lyy_biyesheji.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_submithomework")
public class SubmitHomework {

    @Id
    @GeneratedValue
    private int sh_id;

    /*  布置作业id  */
    @Column(nullable = false)
    private int sh_assignhomeworkid;

    /*  学生提交作业id  */
    @Column(nullable = false)
    private int sh_userid;

    /*  作业提交时间  */
    @Column(nullable = false)
    private Date sh_submittime;

    /*  提交作业文本  */
    @Column
    private String sh_homework;

    /*  提交作业附件 通过url获取这个文件  */
    @Column
    private String sh_homeworkurl;

    public SubmitHomework() {
    }

    public SubmitHomework(SubmitHomework submitHomework) {
        this.sh_id=submitHomework.sh_id;
        this.sh_assignhomeworkid=submitHomework.sh_assignhomeworkid;
        this.sh_userid=submitHomework.sh_userid;
        this.sh_submittime=submitHomework.sh_submittime;
        this.sh_homework=submitHomework.sh_homework;
        this.sh_homeworkurl=submitHomework.sh_homeworkurl;
    }

    public int getSh_id() {
        return sh_id;
    }

    public void setSh_id(int sh_id) {
        this.sh_id = sh_id;
    }

    public int getSh_assignhomeworkid() {
        return sh_assignhomeworkid;
    }

    public void setSh_assignhomeworkid(int sh_assignhomeworkid) {
        this.sh_assignhomeworkid = sh_assignhomeworkid;
    }

    public int getSh_userid() {
        return sh_userid;
    }

    public void setSh_userid(int sh_userid) {
        this.sh_userid = sh_userid;
    }

    public Date getSh_submittime() {
        return sh_submittime;
    }

    public void setSh_submittime(Date sh_submittime) {
        this.sh_submittime = sh_submittime;
    }

    public String getSh_homework() {
        return sh_homework;
    }

    public void setSh_homework(String sh_homework) {
        this.sh_homework = sh_homework;
    }

    public String getSh_homeworkurl() {
        return sh_homeworkurl;
    }

    public void setSh_homeworkurl(String sh_homeworkurl) {
        this.sh_homeworkurl = sh_homeworkurl;
    }

    public static class send_SubmitHomework extends SubmitHomework{
        String username;
        public send_SubmitHomework(SubmitHomework submitHomework,String username){
            super(submitHomework);
            this.username=username;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
