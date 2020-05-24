package lyy_biyesheji.demo.repository;

import lyy_biyesheji.demo.entity.AssignHomework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignhomeworkRepository extends JpaRepository<AssignHomework,Integer> {

    /*  通过班级id查询出班级所有布置的作业  */
    @Query(value="select * from t_assignhomework ah where ah.ah_classid=?1",nativeQuery = true)
    List<AssignHomework> findByAh_classid(Integer classid);



}
