package lyy_biyesheji.demo.repository;

import lyy_biyesheji.demo.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.peer.LightweightPeer;
import java.util.List;

public interface UploadfileRepository extends JpaRepository<UploadFile,Integer> {

    /*  通过老师id查询此老师上传所有文件列表  */
    @Query(value = "select * from t_file tf where tf.f_teacherid=?1",nativeQuery = true)
    List<UploadFile> findByF_teacherid(Integer teacherid);

    /*  通过老师id 班级id 查询此老师上传到此班级所有文件列表  */
    @Query(value = "select * from t_file tf where tf.f_teacherid=?1 and tf.f_classid=?2",nativeQuery = true)
    List<UploadFile> findByF_teacheridAndAndF_classid(Integer teacherid,Integer classid);

    /* 通过班级班级id查询本班级老师上传的文件 */
    @Query(value = "select * from t_file tf where tf.f_classid=?1",nativeQuery = true)
    List<UploadFile>findByF_classid(Integer classid);

}
