package lyy_biyesheji.demo.repository;

import lyy_biyesheji.demo.entity.SubmitHomework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubmithomeworkRepository extends JpaRepository<SubmitHomework,Integer> {

    /*  通过作业id获取此作业 所有提交情况  */
    @Query(value = "select * from t_submithomework ts where ts.sh_assignhomeworkid=?1",nativeQuery = true)
    List<SubmitHomework> findBySh_assignhomeworkid(int sh_ahid);

    /*  通过作业id 学生id 获取此作业学生的提交情况  */
    @Query(value = "select * from t_submithomework ts where ts.sh_assignhomeworkid=?1 and ts.sh_userid",nativeQuery = true)
    List<SubmitHomework> findBySh_assignhomeworkidAndAndSh_userid(int sh_ahid,int sh_userid);

    /*  通过学生id获取此学生 所有作业提交情况 */
    @Query(value = "select * from t_submithomework ts where ts.sh_userid=?1",nativeQuery = true)
    List<SubmitHomework> findBySh_userid(int sh_userid);


}
