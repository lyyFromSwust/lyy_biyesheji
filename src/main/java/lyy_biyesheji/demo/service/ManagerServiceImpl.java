package lyy_biyesheji.demo.service;

import lyy_biyesheji.demo.entity.Manager;
import lyy_biyesheji.demo.entity.User;
import lyy_biyesheji.demo.repository.ManagerRepository;
import lyy_biyesheji.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl {
    @Autowired
    private ManagerRepository managerRepository;

    /*  查询所有用户 */
    public List<Manager> getUserList(){
        return managerRepository.findAll();
    }

    /*  查询单个用户----通过id查询 */
    public Manager getUser(int id){
        return managerRepository.findById(id).get();
    }

    /* 通过用户名，密码做登陆查询 */
    public List<Manager> findByM_nameAndAndM_password(String username,String password){
        return managerRepository.findByM_nameAndAndM_password(username,password);
    }

}
