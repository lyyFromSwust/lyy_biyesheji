package lyy_biyesheji.demo.controller;

import lyy_biyesheji.demo.entity.*;
import lyy_biyesheji.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private ClassServiceImpl classService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserclassServiceImpl userclassService;
    @Autowired
    private UploadfileServiceImpl uploadfileService;
    @Autowired
    private MessageServiceImpl messageService;
    @Autowired
    private LeavemessageServiceImpl leavemessageService;


    @GetMapping("/index")
    public String toStudentIndex(HttpServletRequest request,@CookieValue("userid") String userid,@RequestParam("nowpage") int nowpage, Model model) {
        int teacherid=Integer.parseInt(userid);
        User user=userService.getUser(teacherid);
        List<MClass>mclassList=classService.findByC_teacherid(teacherid);

        model.addAttribute("teacher_name",user.getU_name());
        model.addAttribute("mclasslist",mclassList);

        int newMessageNum=messageService.findByM_aimidAndAndM_isread(teacherid,false).size();
        model.addAttribute("hitNum",newMessageNum);//新消息数量


        int pageNumber=4;
        int page=nowpage-1;
        List<MClass>subMClass=mclassList.subList(page*pageNumber,pageNumber+page*pageNumber<mclassList.size()?pageNumber+page*pageNumber:mclassList.size());

        model.addAttribute("mclasslist",subMClass);
        int modPage=((mclassList.size()%pageNumber!=0)?1:0);
        model.addAttribute("u_allPage",(mclassList.size()/pageNumber+modPage)<=0?1:mclassList.size()/pageNumber+modPage);
        model.addAttribute("u_nowPage",nowpage);

        return "index_teacher";
    }


    @GetMapping("/returnClass")
    public String returnClass(HttpServletRequest request, @CookieValue("userid") String userid,Model model){
        return toStudentIndex(request,userid,1,model);
    }

    @GetMapping("/buildClass")
    public String toBuildClass(Model model){
        MClass mClass=new MClass();
        model.addAttribute("mclass",mClass);
        System.out.println("GetMapping 输出mclass的属性：mclass.teacherid="+mClass.getC_teacherid());
        return "buildClass";
    }

    @PostMapping("/buildClass")
    public String buildClass(HttpServletRequest request,@CookieValue("userid") String userid,@ModelAttribute MClass mclass, Model model){
        int teacherid=Integer.parseInt(userid);
        mclass.setC_teacherid(teacherid);
        /* 先检测班级是否存在，不存在才能创建 */
        List<MClass> classListByTeacherandName=classService.findByC_teacheridAndAndC_classname(teacherid,mclass.getC_classname());
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
    public String classInfo(@CookieValue("userid") String userid,@RequestParam("c_id") int c_id,Model model){
        int u_id=Integer.parseInt(userid);
        /* 如何得到当前这个班级 */
        MClass mClass=classService.getClass(c_id);
        System.out.println("获取从前端传过来的数据c_id：" +c_id);

        /*基本信息*/
        model.addAttribute("user_name",userService.getUser(u_id).getU_name()+"老师");
        model.addAttribute("identy","teacher");

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
    @GetMapping("classStudent")
    public String classStudentInfo(@CookieValue("userid") String userid,@RequestParam("c_id") int c_id,Model model){
        int u_id=Integer.parseInt(userid);
        MClass mClass=classService.getClass(c_id);
        List<User>classStudents=new ArrayList<User>();
        List<UserClass>userClassList=userclassService.findByUc_classid(c_id);
        for(UserClass userClass:userClassList){
            User user=userService.getUser(userClass.getUc_userid());
            classStudents.add(user);
        }
        model.addAttribute("classStudents",classStudents);

        model.addAttribute("user_name",userService.getUser(u_id).getU_name()+"老师");
        model.addAttribute("class_name",mClass.getC_classname());
        int classnum = userclassService.getClassUserNum(mClass.getC_id());
        model.addAttribute("student_number",classnum);
        return "classStudent";
    }

    /*  获得当前页面  */
    @GetMapping("/classFile/{c_id}")
    public String classFile(HttpServletRequest request,@CookieValue("userid") String userid,@PathVariable("c_id") int c_id,Model model){
        /* 得到当前这个班级 */
        MClass mClass=classService.getClass(c_id);
        int teacherid=Integer.parseInt(userid);
        List<UploadFile>files=uploadfileService.findByF_teacheridAndAndF_classid(teacherid,c_id);
        model.addAttribute("fileList",files);

        return "classFile";
    }

    /*  删除文件  */
    @DeleteMapping("/classFile/{c_id}/file/{f_id}")
    public String deleteFile(@PathVariable("c_id")int c_id,@PathVariable("f_id")int f_id)
    {
        uploadfileService.deleteFile(f_id);
        return "redirect:/teacher/classFile/"+c_id;
    }

    /**
     * 实现文件上传
     * */
    @PostMapping("/classFile/{c_id}")
    public String classFileUpload(HttpServletRequest request,@CookieValue("userid") String userid,
                                  @PathVariable("c_id") int c_id,@RequestParam("fileName") MultipartFile file ,Model model) {

        /*  创建新文件  */
        int teacherid=Integer.parseInt(userid);
        UploadFile uploadFile = new UploadFile();
        uploadFile.setF_classid(c_id);
        uploadFile.setF_teacherid(teacherid);

        if(file.isEmpty()){
            model.addAttribute("msg","上传文件为空！");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String path= null;
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        path=path.substring(0,path.length()-15)+"src/main/resources/static/uploadFile/";

        /*
         * System.out.println("默认资源放置位置："+path);
         * 默认资源放置位置：/C:/Users/lenovo/Desktop/spring_demo/target/classes/
         * */
        String oldfileName = file.getOriginalFilename();
        Date date=new Date();
        String format=simpleDateFormat.format(date);
        String newfileName = format+oldfileName;

        /* 设置文件名，上传时间，下载此文件路径 */
        uploadFile.setF_filename(oldfileName);
        uploadFile.setF_uplodatime(date);
        uploadFile.setF_fileurl(path +  newfileName);

        System.out.println("newFilename = "+newfileName);
        File dest = new File(path +  newfileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
            uploadfileService.insertFile(uploadFile);
            model.addAttribute("msg","上传文件成功！");
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            model.addAttribute("msg","文件上传失败！");
        }
        return "result";
    }

    @GetMapping("/classHomeworkList/{c_id}")
    public String classHomeworkList(HttpServletRequest request,@CookieValue("userid") String userid,@PathVariable("c_id") int c_id,Model model){
        return "classHomeworkList";
    }
    @GetMapping("/classMessage/{c_id}")
    public String classMessage(HttpServletRequest request, @CookieValue("userid") String userid, @PathVariable("c_id") int c_id, Model model){
        List<LeaveMessage>leaveMessageList=leavemessageService.findByL_classid(c_id);
        model.addAttribute("leaveMessageList",leaveMessageList);
        model.addAttribute("leaveMessage",new LeaveMessage());
        return "classMessage";
    }

    @PostMapping("/classMessage/{c_id}")
    public String sendLeavemessage(HttpServletRequest request, @CookieValue("userid") String userid, @PathVariable("c_id") int c_id,
                                   @ModelAttribute LeaveMessage leaveMessage,  Model model){
        int teacherid=Integer.parseInt(userid);
        leaveMessage.setL_userid(teacherid);
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

        List<Message>cloneMessage=new ArrayList<Message>();
        for(int i=0;i<messageList.size();i++)cloneMessage.add(messageList.get(i).clone());

        model.addAttribute("user_name",user.getU_name()+"老师");
        model.addAttribute("messageList",cloneMessage);

        messageService.clearUserRead(Integer.parseInt(userid));
        return "message";
    }

    @PostMapping("messageDeal")
    @ResponseBody
    public String MessageDeal(HttpServletRequest request, @CookieValue("userid") String userid,int messageId,String state, Model model){
        Message oldMessage = messageService.getMessage(messageId);
        String returnString="未知错误";
        System.out.println(state);
        if(state.compareTo("accept")==0){
            returnString="接受成功";

            /*添加学生到班级*/
            UserClass userClass=new UserClass();
            userClass.setUc_userid(oldMessage.getM_buildid());
            userClass.setUc_classid(oldMessage.getM_classid());
            userclassService.insertUserClass(userClass);

            /* 加入班级成功消息 */
            Message message=new Message();
            message.setM_buildid(oldMessage.getM_aimid());
            message.setM_aimid(oldMessage.getM_buildid());
            message.setM_classid(oldMessage.getM_classid());
            message.setM_type(3);
            message.setM_issolved(false);
            message.setM_solveresulte(1);
            message.setM_isread(false);
            message.setM_message("加入班级 "+classService.getClass(oldMessage.getM_classid()).getC_classname()+" 成功");
            messageService.insertMessage(message);
        }
        else if(state.compareTo("reject")==0){
            returnString="拒绝成功";

            /* 加入班级失败消息 */
            Message message=new Message();
            message.setM_buildid(oldMessage.getM_aimid());
            message.setM_aimid(oldMessage.getM_buildid());
            message.setM_classid(oldMessage.getM_classid());
            message.setM_type(3);
            message.setM_issolved(false);
            message.setM_solveresulte(1);
            message.setM_isread(false);
            message.setM_message(userService.getUser(oldMessage.getM_aimid()).getU_name() +"拒绝了你加入班级 "+classService.getClass(oldMessage.getM_classid()).getC_classname());
            messageService.insertMessage(message);
        }else{
            return returnString;
        }
        messageService.setDealResult(Integer.parseInt(userid),messageId,state);
        return returnString;
    }
}
