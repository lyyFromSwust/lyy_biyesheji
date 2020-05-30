package lyy_biyesheji.demo.controller;

import lyy_biyesheji.demo.entity.MClass;
import lyy_biyesheji.demo.entity.Message;
import lyy_biyesheji.demo.entity.User;
import lyy_biyesheji.demo.entity.UserClass;
import lyy_biyesheji.demo.service.ClassServiceImpl;
import lyy_biyesheji.demo.service.MessageServiceImpl;
import lyy_biyesheji.demo.service.UserServiceImpl;
import lyy_biyesheji.demo.service.UserclassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private ClassServiceImpl classService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserclassServiceImpl userclassService;
    @Autowired
    private MessageServiceImpl messageService;

    @GetMapping("/index")
    public String toStudentIndex(HttpServletRequest request, @CookieValue("userid") String userid,@RequestParam("nowpage")int nowpage, Model model) {
        int studentid=Integer.parseInt(userid);
        User user=userService.getUser(studentid);
        List<MClass> mclassList=classService.getClassList();
        model.addAttribute("student_name",user.getU_name());
        model.addAttribute("mclasslist",mclassList);

        int newMessageNum=messageService.findByM_aimidAndAndM_isread(studentid,false).size();
        model.addAttribute("hitNum",newMessageNum);//新消息数量

        int pageNumber=4;
        int page=nowpage-1;
        List<MClass>subMClass=mclassList.subList(page*pageNumber,pageNumber+page*pageNumber<mclassList.size()?pageNumber+page*pageNumber:mclassList.size());

        model.addAttribute("mclasslist",subMClass);
        int modPage=((mclassList.size()%pageNumber!=0)?1:0);
        model.addAttribute("u_allPage",(mclassList.size()/pageNumber+modPage)<=0?1:mclassList.size()/pageNumber+modPage);
        model.addAttribute("u_nowPage",nowpage);

        return "index_student";
    }

    /*  学生加入班级  */
    @GetMapping("/joinClass/{c_id}")
    public String joinClass(HttpServletRequest request, @CookieValue("userid") String userid,@PathVariable("c_id")int c_id,Model model) {
        int studentid=Integer.parseInt(userid);
        int teacherid=classService.getClass(c_id).getC_teacherid();;
        /*  查找用户班级关系表是否存在此信息  */
        List<UserClass>userClassList=userclassService.findByUc_classidAndAndUc_userid(c_id,studentid);
        List<Message>messageList=messageService.findByM_buildidAndAndM_classidAndM_type(studentid,c_id,1);
        System.out.println("c_id="+c_id+"   studentid="+studentid);
        /* 没有此条记录才可以申请 */
        if(userClassList.size()==0)
        {
            /* 向老师发送消息 */
            Message message=new Message();
            message.setM_buildid(studentid);
            message.setM_aimid(teacherid);
            message.setM_classid(c_id);
            /*  通知消息类型 m_type
             * (1) 学生申请加入通知、
             * (2) 老师邀请加入通知；
             * (3) 加入班级成功通知；
             * (4) 有人留言通知；
             * (5) 有人提问通知、有人回复通知
             * */
            message.setM_type(1);
            /*  是否处理消息  */
            message.setM_issolved(false);
            /*  处理结果 1.未处理 2.接受 3.拒绝 4.忽略（234都是处理）  */
            message.setM_solveresulte(1);
            message.setM_isread(false);
            String messageText="学生"+userService.getUser(studentid).getU_name()+"申请加入班级"+classService.getClass(c_id).getC_classname();
            message.setM_message(messageText);
            if(messageList.size()==0){
                messageService.insertMessage(message);
                model.addAttribute("msg","已经发出申请，请等待！");
            }
            else{
                model.addAttribute("msg","已经申请成功，无须重复申请！");
            }
        }
        else if(userClassList.size()==1){
            model.addAttribute("msg","你已是班级成员，无须申请！");
        }
        return "result";
    }

    @GetMapping("/myClass")
    public String toMyClass(HttpServletRequest request, @CookieValue("userid") String userid,@RequestParam("nowpage")int nowpage,Model model){
        int studentid=Integer.parseInt(userid);
        List<MClass> myclassList=new ArrayList<MClass>();
        List<UserClass>userClassList=userclassService.findByUc_userid(studentid);
        for(UserClass userClass:userClassList){
            MClass mClass=classService.getClass(userClass.getUc_classid());
            myclassList.add(mClass);
        }
        model.addAttribute("myclassList",myclassList);

        int newMessageNum=messageService.findByM_aimidAndAndM_isread(studentid,false).size();
        model.addAttribute("hitNum",newMessageNum);//新消息数量

        int pageNumber=4;
        int page=nowpage-1;
        List<MClass>subMClass=myclassList.subList(page*pageNumber,pageNumber+page*pageNumber<myclassList.size()?pageNumber+page*pageNumber:myclassList.size());

        model.addAttribute("mclasslist",subMClass);
        int modPage=((myclassList.size()%pageNumber!=0)?1:0);
        model.addAttribute("u_allPage",(myclassList.size()/pageNumber+modPage)<=0?1:myclassList.size()/pageNumber+modPage);
        model.addAttribute("u_nowPage",nowpage);

        return "index_student";
    }

    /*  学生端展示班级信息  */
    @GetMapping("myClass/classInfo/{c_id}")
    public String classInfo(@PathVariable("c_id") int c_id,Model model){
        /* 如何得到当前这个班级 */
        MClass mClass=classService.getClass(c_id);
        System.out.println("获取从前端传过来的数据c_id：" +c_id);
        /* 格式化日期格式 */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        model.addAttribute("classname",mClass.getC_classname());
        model.addAttribute("classtime",simpleDateFormat.format(mClass.getC_buildtime()));
        User user=userService.getUser(mClass.getC_teacherid());

        model.addAttribute("classteacherName",user.getU_name());
        model.addAttribute("classintroduce",mClass.getC_introduce());

        /*  获取当前班级的人数  */
        int classnum = userclassService.getClassUserNum(mClass.getC_id());
        model.addAttribute("classstudentNum",classnum);
        return "classInfo";
    }

    /* 对班级学生展示 */
    @GetMapping("myClass/classStudentInfo/{c_id}")
    public String classStudentInfo(@PathVariable("c_id") int c_id,Model model){
        MClass mClass=classService.getClass(c_id);
        List<User>classStudents=new ArrayList<User>();
        List<UserClass>userClassList=userclassService.findByUc_classid(c_id);
        for(UserClass userClass:userClassList){
            User user=userService.getUser(userClass.getUc_userid());
            classStudents.add(user);
        }
        model.addAttribute("classStudents",classStudents);
        return "classStudent";
    }


}
