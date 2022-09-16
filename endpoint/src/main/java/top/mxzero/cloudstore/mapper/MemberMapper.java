package top.mxzero.cloudstore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import top.mxzero.cloudstore.model.Member;

import java.util.Date;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {
    @Update("update member set login_time = #{date} where id = #{id}")
    void changeLoginDate(@Param("id") Long id, @Param("date") Date date);
}
