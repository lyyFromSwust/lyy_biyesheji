package lyy_biyesheji.demo.controller;

import lyy_biyesheji.demo.entity.MClass;
import lyy_biyesheji.demo.entity.User;
import lyy_biyesheji.demo.service.ClassServiceImpl;
import lyy_biyesheji.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class teacherController{

    @Autowired
    private ClassServiceImpl classService;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/index")
    public String toStudentIndex(@RequestParam("userid") int userid, Model model) {
        User user=userService.getUser(userid);
        List<MClass>mclassList=classService.findByC_teacherid(userid);
        model.addAttribute("teacher_name",user.getU_num());
        model.addAttribute("mclasslist",mclassList);
        return "index_teacher";
    }

    @GetMapping("/build_class")
    public String toBuildClass(@RequestParam("userid")int id,Model model){
        MClass mClass=new MClass();
        mClass.setC_teacherid(id);
        model.addAttribute("mclass",new MClass());
        System.out.println("GetMapping 输出mclass的属性：mclass.teacherid="+mClass.getC_teacherid());
        return "buildClass";
    }
    @PostMapping("/build_class")
    public String buildClass(@RequestParam("userid")int id,@ModelAttribute MClass mclass, Model model){
        /* 先检测班级是否存在，不存在才能创建 */
        List<MClass> classListByTeacherandName=classService.findByC_teacheridAndAndC_classname(id,mclass.getC_classname());
        System.out.println("PostMapping 输出mclass的属性：mclass.teacherid="+mclass.getC_teacherid());
        if(classListByTeacherandName.size()==0){
            classService.insertClass(mclass);
            model.addAttribute("msg","创建班级成功！");
        }
        else{
            model.addAttribute("msg","已有班级存在，请重新创建班级！");
        }
        return "result";
    }

    @GetMapping("/classInfo")
    public String classInfo(Model model){
        model.addAttribute("classname","自动化测试");
        model.addAttribute("classtime","2020-05-20 14:44");
        model.addAttribute("classteacherName","htp");
        model.addAttribute("classintroduce","创建班级成功！这是一门课的班级介绍");
        model.addAttribute("classstudentNum","88");
        return "classInfo";
    }

    @GetMapping("/classFile")
    public String classFile(Model model){
        return "classFile";
    }
    @GetMapping("/classHomeworkList")
    public String classHomeworkList(Model model){
        return "classHomeworkList";
    }
    @GetMapping("/classMessage")
    public String classMessage(Model model){
        return "classMessage";
    }

}
