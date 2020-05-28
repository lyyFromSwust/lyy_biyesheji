package lyy_biyesheji.demo.controller;

import lyy_biyesheji.demo.entity.MClass;
import lyy_biyesheji.demo.entity.UploadFile;
import lyy_biyesheji.demo.entity.User;
import lyy_biyesheji.demo.service.ClassServiceImpl;
import lyy_biyesheji.demo.service.UploadfileServiceImpl;
import lyy_biyesheji.demo.service.UserServiceImpl;
import lyy_biyesheji.demo.service.UserclassServiceImpl;
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


    @GetMapping("/index")
    public String toStudentIndex(HttpServletResponse response, HttpServletRequest request,@CookieValue("userid") String userid, Model model) {
        int teacherid=Integer.parseInt(userid);
        User user=userService.getUser(teacherid);
        List<MClass>mclassList=classService.findByC_teacherid(teacherid);

        /* 添加cookie */
        for(MClass mClass:mclassList){
            Cookie cookie=new Cookie("classid"+mClass.getC_id(),String.valueOf(mClass.getC_id()));
            response.addCookie(cookie);
        }

        model.addAttribute("teacher_name",user.getU_name());
        model.addAttribute("mclasslist",mclassList);
        return "index_teacher";
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

    @GetMapping("/classInfo/{c_id}")
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

    @GetMapping("/classHomeworkList")
    public String classHomeworkList(Model model){
        return "classHomeworkList";
    }
    @GetMapping("/classMessage")
    public String classMessage(Model model){
        return "classMessage";
    }

}
