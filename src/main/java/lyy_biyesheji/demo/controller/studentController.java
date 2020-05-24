package lyy_biyesheji.demo.controller;

import lyy_biyesheji.demo.entity.MClass;
import lyy_biyesheji.demo.entity.User;
import lyy_biyesheji.demo.service.ClassServiceImpl;
import lyy_biyesheji.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class studentController {
    @Autowired
    private ClassServiceImpl classService;
    @Autowired
    private UserServiceImpl userService;
    private int userId;

    @GetMapping("/student/index")
    public String toStudentIndex(@RequestParam("userid") int userid, Model model) {

            User user=userService.getUser(userid);
            List<MClass> mclassList=classService.getClassList();
            model.addAttribute("student_name",user.getU_num());
            model.addAttribute("mclasslist",mclassList);
        return "index_student";
    }
}
