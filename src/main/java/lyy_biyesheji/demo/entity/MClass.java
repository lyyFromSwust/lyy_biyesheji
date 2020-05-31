package lyy_biyesheji.demo.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="t_class")
public class MClass {

    @Id
    @GeneratedValue
    private int c_id;

    @Column(nullable = false)
    private int c_teacherid;

    @Column(nullable = false)
    private String c_classname;
    @Column
    private String c_introduce;

    @Column(nullable = false)
    private Date c_buildtime;
    public MClass() {
    }

    public MClass(int c_teacherid, String c_classname, String c_introduce, Date c_buildtime) {
        this.c_teacherid = c_teacherid;
        this.c_classname = c_classname;
        this.c_introduce = c_introduce;
        this.c_buildtime = c_buildtime;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getC_teacherid() {
        return c_teacherid;
    }

    public void setC_teacherid(int c_teacherid) {
        this.c_teacherid = c_teacherid;
    }

    public String getC_classname() {
        return c_classname;
    }

    public void setC_classname(String c_classname) {
        this.c_classname = c_classname;
    }

    public String getC_introduce() {
        return c_introduce;
    }

    public void setC_introduce(String c_introduce) {
        this.c_introduce = c_introduce;
    }

    public Date getC_buildtime() {
        return c_buildtime;
    }

    public void setC_buildtime(Date c_buildtime) {
        this.c_buildtime = c_buildtime;
    }

    @Override
    public String toString() {
        return "MClass{" +
                "c_id=" + c_id +
                ", c_teacherid=" + c_teacherid +
                ", c_classname='" + c_classname + '\'' +
                ", c_introduce='" + c_introduce + '\'' +
                ", c_buildtime=" + c_buildtime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MClass)) return false;
        MClass mClass = (MClass) o;
        return c_id == mClass.c_id &&
                c_teacherid == mClass.c_teacherid &&
                c_classname.equals(mClass.c_classname) &&
                c_introduce.equals(mClass.c_introduce) &&
                c_buildtime.equals(mClass.c_buildtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(c_id, c_teacherid, c_classname, c_introduce, c_buildtime);
    }


    public static class send_MClass extends MClass{

        private String c_teacherName;
        public send_MClass(){}
        public send_MClass(MClass mClass){
            super(mClass.getC_teacherid(),mClass.getC_classname(),mClass.getC_introduce(),mClass.getC_buildtime());
        }
        public send_MClass(MClass mClass,String teacherName){
            super(mClass.getC_teacherid(),mClass.getC_classname(),mClass.getC_introduce(),mClass.getC_buildtime());
            super.setC_id(mClass.c_id);
            c_teacherName=teacherName;
        }

        public String getC_teacherName() {
            return c_teacherName;
        }

        public void setC_teacherName(String c_teacherName) {
            this.c_teacherName = c_teacherName;
        }
    }

    public static  class  send_MClass_plus extends send_MClass{
        private int inClass;
        public send_MClass_plus(MClass mClass,String teacherName,int isInClass){
            super(mClass,teacherName);
            this.inClass=isInClass;
        }

        public int getInClass(){
            return inClass;
        }

        public void setInClass(int inClass) {
            this.inClass = inClass;
        }
    }
}

