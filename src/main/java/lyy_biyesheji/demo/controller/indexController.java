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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String index(HttpServletRequest request){
        /* 销毁cookie */

        return "index";
    }

    @GetMapping("/message")
    public String test(HttpServletRequest request){
        return "message";
    }

    @GetMapping("/index_visitor/{nowpage}")
    public String toVisitor(@PathVariable("nowpage")int nowpage, Model model) {
        List<MClass>mclassList=classService.getClassList();
        int pageNumber=3;
        int page=nowpage-1;
        List<MClass>subMClass=mclassList.subList(page*pageNumber,pageNumber+page*pageNumber<mclassList.size()?pageNumber+page*pageNumber:mclassList.size());

        model.addAttribute("mclasslist",subMClass);
        int modPage=((mclassList.size()%pageNumber!=0)?1:0);
        model.addAttribute("u_allPage",(mclassList.size()/pageNumber+modPage)<=0?1:mclassList.size()/pageNumber+modPage);
        model.addAttribute("u_nowPage",nowpage);
        return "index_visitor";
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("user",new User());
        return "login";
    }

    /*  用户登陆 */
    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute User user, Model model, HttpServletResponse response){

        /* 先检测用户是否存在且只有一个*/
        List<User> userList=userService.findByU_numAndAndU_passwordAndAndU_identity(
                user.getU_num(),user.getU_password(),user.getU_identity());
        if(userList.size()==1){
            int u_id=userList.get(0).getU_id();
            user.setU_id(u_id);
            /*  添加cookie获取用户id  */
            Cookie cookie=new Cookie("userid",String.valueOf(u_id));
            response.addCookie(cookie);
            if(user.getU_identity()==false){
                return "redirect:/student/index?nowpage="+1;
            }
            else {
                return String.format("redirect:/teacher/index?nowpage=%d",1);
                //return "redirect:/teacher/index";
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


}
