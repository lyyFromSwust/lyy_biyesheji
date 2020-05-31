package lyy_biyesheji.demo.controller;

import lyy_biyesheji.demo.entity.*;
import lyy_biyesheji.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    @Autowired
    private UploadfileServiceImpl uploadfileService;
    @Autowired
    private LeavemessageServiceImpl leavemessageService;

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

        int modPage=((mclassList.size()%pageNumber!=0)?1:0);
        model.addAttribute("u_allPage",(mclassList.size()/pageNumber+modPage)<=0?1:mclassList.size()/pageNumber+modPage);
        model.addAttribute("u_nowPage",nowpage);

        /*获取教师姓名*/
        List<MClass.send_MClass>send_subMClass=new ArrayList<MClass.send_MClass>();
        for(int i=0;i<subMClass.size();i++)send_subMClass.add(
                new MClass.send_MClass(
                        subMClass.get(i),
                        userService.getUser(subMClass.get(i).getC_teacherid()).getU_name()));
        model.addAttribute("mclasslist",send_subMClass);
        System.out.println(subMClass.get(0).getC_id());
        System.out.println(send_subMClass.get(0).getC_id());
        return "index_student";
    }

    /*  学生加入班级  */
    @PostMapping("/joinClass/{c_id}")
    @ResponseBody
    public String joinClass(HttpServletRequest request, @CookieValue("userid") String userid,@PathVariable("c_id")int c_id,Model model) {
        String returnString="未知错误";
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
            message.setM_type(1);
            /*  是否处理消息  */
            message.setM_issolved(false);
            /*  处理结果 1.未处理 2.接受 3.拒绝 4.忽略（234都是处理）  */
            message.setM_solveresulte(1);
            message.setM_isread(false);
            String messageText="学生"+userService.getUser(studentid).getU_name()+"申请加入班级"+classService.getClass(c_id).getC_classname();
            message.setM_message(messageText);
            boolean allowJoin=true;
            for(int i=0;i<messageList.size();i++){
                if(messageList.get(i).getM_solveresulte()==1){
                    allowJoin=false;
                    break;
                }
            }

            if(allowJoin){
                messageService.insertMessage(message);
                returnString="已经发出申请，请等待！";
//                model.addAttribute("msg","已经发出申请，请等待！");
            }
            else{
//                model.addAttribute("msg","已经申请成功，无须重复申请！");
                returnString="已经申请成功，无须重复申请！";
            }
        }
        else if(userClassList.size()==1){
//            model.addAttribute("msg","你已是班级成员，无须申请！");
            returnString="你已是班级成员，无须申请！";
        }
        return returnString;
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

        User user=userService.getUser(studentid);
        List<MClass> mclassList=classService.getClassList();
        model.addAttribute("student_name",user.getU_name());

        int newMessageNum=messageService.findByM_aimidAndAndM_isread(studentid,false).size();
        model.addAttribute("hitNum",newMessageNum);//新消息数量

        int pageNumber=4;
        int page=nowpage-1;
        List<MClass>subMClass=myclassList.subList(page*pageNumber,pageNumber+page*pageNumber<myclassList.size()?pageNumber+page*pageNumber:myclassList.size());
        int modPage=((myclassList.size()%pageNumber!=0)?1:0);
        model.addAttribute("u_allPage",(myclassList.size()/pageNumber+modPage)<=0?1:myclassList.size()/pageNumber+modPage);
        model.addAttribute("u_nowPage",nowpage);

        /*获取教师姓名*/
        List<MClass.send_MClass>send_subMClass=new ArrayList<MClass.send_MClass>();
        for(int i=0;i<subMClass.size();i++)send_subMClass.add(
                new MClass.send_MClass(
                        subMClass.get(i),
                        userService.getUser(subMClass.get(i).getC_teacherid()).getU_name()));
        model.addAttribute("mclasslist",send_subMClass);

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
        List<User>classStudents=new ArrayList<User>();
        List<UserClass>userClassList=userclassService.findByUc_classid(c_id);
        for(UserClass userClass:userClassList){
            User user=userService.getUser(userClass.getUc_userid());
            classStudents.add(user);
        }
        model.addAttribute("classStudents",classStudents);
        return "classStudent";
    }

    /* 进入作业界面 */
    @GetMapping("/classHomeworkList/{c_id}")
    public String classHomeworkList(HttpServletRequest request,@CookieValue("userid") String userid,@PathVariable("c_id") int c_id,Model model){
        return "classHomeworkList";
    }


    /*  进入文件页面  */
    @GetMapping("myClass/classFile/{c_id}")
    public String classFile(HttpServletRequest request,@PathVariable("c_id") int c_id,Model model){
        /* 得到当前这个班级 */
        MClass mClass=classService.getClass(c_id);
        List<UploadFile>files=uploadfileService.findByF_classid(c_id);
        model.addAttribute("fileList",files);

        return "classFile";
    }

    /* 留言显示 */
    @GetMapping("myClass/classMessage/{c_id}")
    public String classMessage(HttpServletRequest request,@PathVariable("c_id") int c_id, Model model){
        List<LeaveMessage>leaveMessageList=leavemessageService.findByL_classid(c_id);
        model.addAttribute("leaveMessageList",leaveMessageList);
        model.addAttribute("leaveMessage",new LeaveMessage());
        return "classMessage";
    }

    /* 留言发送 */
    @PostMapping("myClass/classMessage/{c_id}")
    public String sendLeavemessage(HttpServletRequest request, @CookieValue("userid") String userid, @PathVariable("c_id") int c_id,
                                   @ModelAttribute LeaveMessage leaveMessage,  Model model){
        int studentid=Integer.parseInt(userid);
        leaveMessage.setL_userid(studentid);
        leaveMessage.setL_classid(c_id);
        System.out.println(leaveMessage.getL_leavemessage());
        leavemessageService.insertLeavemessage(leaveMessage);
        model.addAttribute("msg","留言成功");
        return "result";
    }



    @GetMapping("/message")
    public String Message(HttpServletRequest request, @CookieValue("userid") String userid, Model model){
        User user=userService.getUser(Integer.parseInt(userid));
        List<MClass> mclassList=classService.getClassList();
        model.addAttribute("user_name",user.getU_name());

        List<Message> messageList=messageService.findByM_aimid(Integer.parseInt(userid));
        Collections.reverse(messageList);
        int newNum=messageService.findByM_aimidAndAndM_isread(Integer.parseInt(userid),false).size();
        int maxShowNum=5;
        int endShowNum=Math.max(maxShowNum,newNum);
        endShowNum=Math.min(endShowNum,messageList.size());
        messageList.subList(0,endShowNum);

//        System.out.println(messageList.get(0).getm_);

        model.addAttribute("user_name",user.getU_name()+"同学");
        model.addAttribute("messageList",messageList);

        messageService.clearUserRead(Integer.parseInt(userid));
        return "message";
    }


    @PostMapping("messageDeal")
    @ResponseBody
    public String MessageDeal(HttpServletRequest request, @CookieValue("userid") String userid,int messageId,String state, Model model){
        System.out.println(messageId);
        System.out.println(state);
        String returnString="未知错误";
        if(state.compareTo("accept") == 0){
            returnString="接受成功";
        }
        else if(state.compareTo("reject") == 0){
            returnString="拒绝成功";
        }else{
            return returnString;
        }
        messageService.setDealResult(Integer.parseInt(userid),messageId,state);
        return returnString;
    }
}
