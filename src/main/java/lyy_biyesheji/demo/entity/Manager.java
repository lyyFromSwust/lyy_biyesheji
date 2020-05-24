package lyy_biyesheji.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="t_manager")
public class Manager {

    @Id
    @GeneratedValue
    private int m_id;

    @Column(nullable = false,unique = true)
    private String m_name;

    @Column(nullable = false)
    private String m_password;

    public Manager() {
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_password() {
        return m_password;
    }

    public void setM_password(String m_password) {
        this.m_password = m_password;
    }
}
