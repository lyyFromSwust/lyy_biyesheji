package lyy_biyesheji.demo.controller;

import lyy_biyesheji.demo.entity.MClass;
import lyy_biyesheji.demo.entity.Manager;
import lyy_biyesheji.demo.entity.User;
import lyy_biyesheji.demo.service.ClassServiceImpl;
import lyy_biyesheji.demo.service.ManagerServiceImpl;
import lyy_biyesheji.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class indexController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ClassServiceImpl classService;

    @Autowired
    private ManagerServiceImpl managerService;

    @GetMapping("/")
    public String index(Model model){
        List<MClass>mclassList=classService.getClassList();
        model.addAttribute("mclasslist",mclassList);
        return "index";
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("user",new User());
        return "login";
    }

    /*  用户登陆 */
    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute User user,Model model){

        /* 先检测用户是否存在且只有一个*/
        List<User> userList=userService.findByU_numAndAndU_passwordAndAndU_identity(
                user.getU_num(),user.getU_password(),user.getU_identity());
        if(userList.size()==1){
            int u_id=userList.get(0).getU_id();
            user.setU_id(u_id);
            if(user.getU_identity()==false){
                return String.format("redirect:/student/index?userid=%d",user.getU_id());
            }
            else {
                return String.format("redirect:/teacher/index?userid=%d",user.getU_id());
            }
        }
        else if(userList.size()==0){
            model.addAttribute("msg","账号不存在，请重新输入");
        }
        else{
            model.addAttribute("msg","账号密码有误，请重新输入");
        }
        return "result";
    }



    @GetMapping("/registe")
    public String registeForm(Model model){
        model.addAttribute("user",new User());
        return "registe";
    }

    /*  插入用户 */
    @PostMapping("/registe")
    public String registeSubmit(@ModelAttribute User user, Model model){

        /* 先检测用户是否存在，不存在才能插入 */
        List<User> userList=userService.findByU_numAndAndU_identity(user.getU_num(),user.getU_identity());
        if(userList.size()==0){
            userService.insertUser(user);
            model.addAttribute("msg","注册成功！");
        }
        else{
            model.addAttribute("msg","已有用户注册，请重新注册！");
        }
        return "result";
    }




    @GetMapping("/manager")
    public String manageLoginForm(Model model){
        model.addAttribute("manager",new Manager());
        return "managerLogin";
    }

    /*  管理员登陆 */
    @RequestMapping(value = "/manager",method = RequestMethod.POST)
    public String managerSubmit(@ModelAttribute Manager manager, Model model){
        /* 先检测管理员密码账号是否符合 */
        System.out.printf("接受到的数据为："+manager.getM_name()+manager.getM_password());
        List<Manager> managerList=managerService.findByM_nameAndAndM_password(manager.getM_name(),manager.getM_password());
        if(managerList.size()==1){
            int m_id=managerList.get(0).getM_id();
            List<User>userList=userService.getUserList();
            model.addAttribute("m_name",manager.getM_name());
            model.addAttribute("users",userList);
            return "userList";
        }
        else if(managerList.size()==0){
            model.addAttribute("msg","账号不存在，请重新输入");
        }
        else{
            model.addAttribute("msg","账号密码有误，请重新输入");
        }
        return "result";
    }


    //    /*插入一个班级测试——成功 */
//    @ResponseBody
//    @GetMapping("/registe_class")
//    public String insertClass(){
//        String result=null;
//        MClass mClass=new MClass();
//        mClass.setC_teacherid(1);
//        mClass.setC_introduce("介绍ooooo");
//        mClass.setC_classname("数据库设计");
//
//        classService.insertClass(mClass);
//
//        result+="c_id: "+mClass.getC_id()+"\n";
//        result+="c_teacherid: "+mClass.getC_teacherid()+"\n";
//        result+="c_classname: "+mClass.getC_classname()+"\n";
//        result+="c_introduce: "+mClass.getC_introduce()+"\n";
//        result+="c_buildTime: "+mClass.getC_buildtime().toString()+"插入成功\n";
//
//        return result;
//    }
}
