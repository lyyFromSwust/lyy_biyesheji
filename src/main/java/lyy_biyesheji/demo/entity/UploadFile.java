package lyy_biyesheji.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_file")
public class UploadFile {
    @Id
    @GeneratedValue
    private int f_id;

    /*  用户id 上传老师id */
    @Column(nullable = false)
    private int f_teacherid;

    /*  班级id 班级资料 */
    @Column(nullable = false)
    private int f_classid;

    /*  文件名 文件标题  */
    @Column(nullable = false)
    private String f_filename;

    /*  文件url路径  */
    @Column(nullable = false)
    private String f_fileurl;

    /*  上传时间  */
    @Column
    private Date f_uplodatime;

    public UploadFile() {
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public int getF_teacherid() {
        return f_teacherid;
    }

    public void setF_teacherid(int f_teacherid) {
        this.f_teacherid = f_teacherid;
    }

    public int getF_classid() {
        return f_classid;
    }

    public void setF_classid(int f_classid) {
        this.f_classid = f_classid;
    }

    public String getF_filename() {
        return f_filename;
    }

    public void setF_filename(String f_filename) {
        this.f_filename = f_filename;
    }

    public String getF_fileurl() {
        return f_fileurl;
    }

    public void setF_fileurl(String f_fileurl) {
        this.f_fileurl = f_fileurl;
    }

    public Date getF_uplodatime() {
        return f_uplodatime;
    }

    public void setF_uplodatime(Date f_uplodatime) {
        this.f_uplodatime = f_uplodatime;
    }

    @Override
    public String toString() {
        return "UploadFile{" +
                "f_id=" + f_id +
                ", f_teacherid=" + f_teacherid +
                ", f_classid=" + f_classid +
                ", f_filename='" + f_filename + '\'' +
                ", f_fileurl='" + f_fileurl + '\'' +
                ", f_uplodatime=" + f_uplodatime +
                '}';
    }
}
