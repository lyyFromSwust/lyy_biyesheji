package lyy_biyesheji.demo.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="t_user")
public class User {

    @Id
    @GeneratedValue
    private int u_id;

    @Column(nullable = false)
    private String u_num;

    /*  0是学生 1是老师 */
    @Column(nullable = false)
    private boolean u_identity;
    
    @Column(nullable = false)
    private String u_name;

    @Column(nullable = false)
    private String u_password;

    @Column(nullable = false)
    private Date u_registetime;

    public User() {
    }

    public User(String u_num, boolean u_identity, String u_name, String u_password, Date u_registetime) {
        this.u_num = u_num;
        this.u_identity = u_identity;
        this.u_name = u_name;
        this.u_password = u_password;
        this.u_registetime = u_registetime;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_num() {
        return u_num;
    }

    public void setU_num(String u_username) {
        this.u_num = u_username;
    }

    public boolean getU_identity() {
        return u_identity;
    }

    public void setU_identity(boolean u_identity) {
        this.u_identity = u_identity;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_nickname) {
        this.u_name = u_nickname;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public Date getU_registetime() {
        return u_registetime;
    }

    public void setU_registetime(Date u_registetime) {
        this.u_registetime = u_registetime;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id=" + u_id +
                ", u_username='" + u_num + '\'' +
                ", u_identity='" + u_identity + '\'' +
                ", u_nickname='" + u_name + '\'' +
                ", u_password='" + u_password + '\'' +
                ", u_registetime=" + u_registetime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return u_id == user.u_id &&
                u_identity == user.u_identity &&
                u_num.equals(user.u_num) &&
                u_name.equals(user.u_name) &&
                u_password.equals(user.u_password) &&
                u_registetime.equals(user.u_registetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(u_id, u_num, u_identity, u_name, u_password, u_registetime);
    }
}
