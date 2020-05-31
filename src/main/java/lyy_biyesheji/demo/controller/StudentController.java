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
    @Autowired
    private AssignhomeworkServiceImpl assignhomeworkService;

    @GetMapping("/index")
    public String toStudentIndex(HttpServletRequest request, @CookieValue("userid") String userid, @RequestParam("nowpage")int nowpage, Model model) {
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
        List<MClass.send_MClass_plus>send_subMClass=new ArrayList<MClass.send_MClass_plus>();
        for(int i=0;i<subMClass.size();i++)send_subMClass.add(
                new MClass.send_MClass_plus(
                        subMClass.get(i),
                        userService.getUser(subMClass.get(i).getC_teacherid()).getU_name(),
                        userclassService.findByUc_classidAndAndUc_userid(subMClass.get(i).getC_id(),studentid).size()));
        model.addAttribute("mclasslist",send_subMClass);
        return "index_student";
    }
    /*搜索页面*/
    @PostMapping("/index")
    public String toStudentIndexPost(HttpServletRequest request, @CookieValue("userid") String userid, @RequestParam("nowpage")int nowpage, Model model) {
        int studentid=Integer.parseInt(userid);
        User user=userService.getUser(studentid);
        List<MClass> mclassList=null;

        String searchkey=request.getParameter("searchclass");

        System.out.println("searchkey="+searchkey);

        /*搜索班级*/
        if(searchkey==null){
            mclassList=classService.getClassList();
        }
        else{
            mclassList=classService.findByC_classname(searchkey);
        }
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
        List<MClass.send_MClass_plus>send_subMClass=new ArrayList<MClass.send_MClass_plus>();
        for(int i=0;i<subMClass.size();i++)send_subMClass.add(
                new MClass.send_MClass_plus(
                        subMClass.get(i),
                        userService.getUser(subMClass.get(i).getC_teacherid()).getU_name(),
                        userclassService.findByUc_classidAndAndUc_userid(subMClass.get(i).getC_id(),studentid).size()));
        model.addAttribute("mclasslist",send_subMClass);

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
        List<MClass.send_MClass_plus>send_subMClass=new ArrayList<MClass.send_MClass_plus>();
        for(int i=0;i<subMClass.size();i++)send_subMClass.add(
                new MClass.send_MClass_plus(
                        subMClass.get(i),
                        userService.getUser(subMClass.get(i).getC_teacherid()).getU_name(),
                        1));
        model.addAttribute("mclasslist",send_subMClass);

        return "index_student";
    }

    @GetMapping("/returnClass")
    public String returnClass(HttpServletRequest request, @CookieValue("userid") String userid,Model model){
        return toMyClass(request,userid,1,model);
    }

    @GetMapping("myClass/returnClass")
    public String returnClass_myClass(HttpServletRequest request, @CookieValue("userid") String userid,Model model){
        return toMyClass(request,userid,1,model);
    }

    /*  学生端展示班级信息  */
    @GetMapping("classInfo")
    public String classInfo(@CookieValue("userid") String userid, @RequestParam("c_id") int c_id,Model model){
        System.out.println("输出班级信息：c_id"+c_id);
        int studentid=Integer.parseInt(userid);
        /* 如何得到当前这个班级 */
        MClass mClass=classService.getClass(c_id);
        System.out.println("获取从前端传过来的数据c_id：" +c_id);

        model.addAttribute("user_name",userService.getUser(studentid).getU_name()+"同学");
        model.addAttribute("identy","student");
        /* 格式化日期格式 */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
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
    @GetMapping("classStudentInfo")
    public String classStudentInfo(@CookieValue("userid") String userid,@RequestParam("c_id") int c_id,Model model){
        int u_id=Integer.parseInt(userid);
        List<User>classStudents=new ArrayList<User>();
        List<UserClass>userClassList=userclassService.findByUc_classid(c_id);
        for(UserClass userClass:userClassList){
            User user=userService.getUser(userClass.getUc_userid());
            classStudents.add(user);
        }
        MClass mClass=classService.getClass(c_id);
        model.addAttribute("classStudents",classStudents);
        model.addAttribute("user_name",userService.getUser(u_id).getU_name()+"同学");
        model.addAttribute("class_name",mClass.getC_classname());
        return "classStudent";
    }

    /* 进入作业界面 */
    @GetMapping("myClass/classHomeworkList/{c_id}")
    public String classHomeworkList(@PathVariable("c_id") int c_id,Model model){
        List<AssignHomework>assignHomeworkList=assignhomeworkService.findByAh_classid(c_id);
        model.addAttribute("homeworklist",assignHomeworkList);
        model.addAttribute("ahhomework",new AssignHomework());
        return "classHomeworkList";
    }



    /*  进入文件页面  */
    @GetMapping("classFile")
    public String classFile(HttpServletRequest request,@CookieValue("userid") String userid,@RequestParam("c_id") int c_id,Model model){
        int u_id=Integer.parseInt(userid);
        /* 得到当前这个班级 */
        MClass mClass=classService.getClass(c_id);
        List<UploadFile>files=uploadfileService.findByF_classid(c_id);
        model.addAttribute("fileList",files);

        model.addAttribute("isTeacher",0);
        model.addAttribute("class_name",mClass.getC_classname());
        model.addAttribute("user_name",userService.getUser(u_id).getU_name()+"同学");
        model.addAttribute("file_number",files.size());
        return "classFile";
    }

    /* 留言显示 */
    @GetMapping("classLeaveMessage")
    public String classMessage(HttpServletRequest request,@CookieValue("userid") String userid,@RequestParam("c_id") int c_id,@RequestParam("nowpage") int nowpage, Model model){
        List<LeaveMessage>leaveMessageList=leavemessageService.findByL_classid(c_id);
        Collections.reverse(leaveMessageList);
        MClass mClass=classService.getClass(c_id);
        int u_id=Integer.parseInt(userid);

        model.addAttribute("class_name",mClass.getC_classname());
        model.addAttribute("user_name",userService.getUser(u_id).getU_name()+"同学");

        int pageNumber=4;
        int page=nowpage-1;
        List<LeaveMessage>subLeaveMessage=leaveMessageList.subList(page*pageNumber,pageNumber+page*pageNumber<leaveMessageList.size()?pageNumber+page*pageNumber:leaveMessageList.size());

        int modPage=((leaveMessageList.size()%pageNumber!=0)?1:0);
        model.addAttribute("u_allPage",(leaveMessageList.size()/pageNumber+modPage)<=0?1:leaveMessageList.size()/pageNumber+modPage);
        model.addAttribute("u_nowPage",nowpage);

        model.addAttribute("leaveMessage",new LeaveMessage());

        List<LeaveMessage.send_LeaveMessage>sendMsg=new ArrayList<LeaveMessage.send_LeaveMessage>();
        for(int i=0;i<subLeaveMessage.size();i++)sendMsg.add(new LeaveMessage.send_LeaveMessage(
                subLeaveMessage.get(i),
                userService.getUser(subLeaveMessage.get(i).getL_userid()).getU_name()
                ));
        model.addAttribute("leaveMessageList",sendMsg);
        System.out.println(subLeaveMessage.get(0).getL_leavemessage());
        System.out.println(sendMsg.get(0).getL_leavemessage());
        return "classLeaveMessage";
    }

    /* 留言发送 */
    @PostMapping("classLeaveMessage")
    public String sendLeavemessage(HttpServletRequest request, @CookieValue("userid") String userid, @RequestParam("c_id") int c_id,
                                   @ModelAttribute LeaveMessage leaveMessage,  Model model){
        int studentid=Integer.parseInt(userid);
        MClass mClass=classService.getClass(c_id);

        leaveMessage.setL_userid(studentid);
        leaveMessage.setL_classid(c_id);

        leavemessageService.insertLeavemessage(leaveMessage);
        model.addAttribute("msg","留言成功");
        /*向老师发送一条新留言消息*/
        Message message=new Message();
        message.setM_buildid(studentid);
        message.setM_aimid(mClass.getC_teacherid());
        message.setM_classid(c_id);
        message.setM_type(4);
        message.setM_issolved(false);
        message.setM_isread(false);
        message.setM_solveresulte(1);
        message.setM_message("学生  "+userService.getUser(studentid).getU_name()+" 在班级 "+classService.getClass(c_id).getC_classname()+" 留言了");
        messageService.insertMessage(message);

        return classMessage(request,userid,c_id,1,model);
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
