package lyy_biyesheji.demo.controller;

import lyy_biyesheji.demo.entity.MClass;
import lyy_biyesheji.demo.service.ClassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassServiceImpl classService;

    /*  获取所有班级 */
    @GetMapping
    public List<MClass> getClassList() {
        System.out.println("从数据库中访问User集合");
        return classService.getClassList();
    }

    /*  获取单个班级 */
    @GetMapping("{id}")
    public MClass getUser(@PathVariable("id")int id){
        return classService.getClass(id);
    }

    /* 创建班级  */
    @PostMapping
    public MClass insertUser(@RequestBody MClass mClass){
        return classService.insertClass(mClass);
    }

    /* 更新班级  */
    @PatchMapping
    public MClass updateUser(@RequestBody MClass mClass){
        return classService.updateClass(mClass);
    }

    /* 删除班级  */
    @DeleteMapping("{id}")
    public boolean deleteUser(@PathVariable("id")int id){
        classService.deleteClass(id);
        return true;
    }
}
