package lyy_biyesheji.demo.controller;


import lyy_biyesheji.demo.entity.User;
import lyy_biyesheji.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /*  获取所有用户 */
    @GetMapping
    public List<User> getUserList() {
        System.out.println("从数据库中访问User集合");
        return userService.getUserList();
    }

    /*  获取单个用户 */
    @GetMapping("{id}")
    public User getUser(@PathVariable("id")int id){
        return userService.getUser(id);
    }

    /* 创建用户  */
    @PostMapping
    public User insertUser(@RequestBody User user){
        return userService.insertUser(user);
    }

    /* 更新用户  */
    @PatchMapping
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    /* 删除用户  */
    @DeleteMapping("{id}")
    public boolean deleteUser(@PathVariable("id")int id){
        userService.deleteUser(id);
        return true;
    }

}

