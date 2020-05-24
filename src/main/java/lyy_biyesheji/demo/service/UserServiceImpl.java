package lyy_biyesheji.demo.service;


import lyy_biyesheji.demo.entity.User;
import lyy_biyesheji.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/* UserService层方法，实现增删改查的业务逻辑实现 */
@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    /*  查询所有用户 */
    public List<User> getUserList(){
        return userRepository.findAll();
    }

    /*  查询单个用户----通过id查询 */
    public User getUser(int id){
        return userRepository.findById(id).get();
    }


    /*  创建用户 */
    public User insertUser(User user){
        user.setU_registetime(new Date());
        return userRepository.save(user);
    }

    /*  修改用户 */
    public User updateUser(User user){
        User user1 = userRepository.findById(user.getU_id()).get();
        if(user1==null){
            return null;
        }
        user1.setU_num(user.getU_num());
        user1.setU_password(user.getU_password());
        return userRepository.save(user1);
    }

    /*  删除用户 */
    public boolean deleteUser(int id){
        try{
            userRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /* 通过学号，密码，身份做登陆查询 */
    public List<User> findByU_numAndAndU_passwordAndAndU_identity(String num,String password,Boolean identity){
        return userRepository.findByU_numAndAndU_passwordAndAndU_identity(num,password,identity);
    }

    /* 通过学号，身份做注册查询 */
    public List<User> findByU_numAndAndU_identity(String num,Boolean identity){
        return userRepository.findByU_numAndAndU_identity(num,identity);
    }

}

