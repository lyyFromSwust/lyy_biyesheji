package lyy_biyesheji.demo.repository;


import lyy_biyesheji.demo.entity.MClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassRepository extends JpaRepository<MClass, Integer> {

    /* 老师界面班级查询 通过老师id查询班级*/
    @Query(value = "select * from t_class cc where cc.c_teacherid =?1",nativeQuery = true)
    List<MClass> findByC_teacherid(Integer teacherid);

    /* 老师创建班级查询 通过老师id和课程名称判断是否重名 */
    @Query(value = "select * from t_class cc where cc.c_teacherid =?1 and cc.c_classname =?2",nativeQuery = true)
    List<MClass> findByC_teacheridAndAndC_classname(Integer teacherid,String classname);

}
