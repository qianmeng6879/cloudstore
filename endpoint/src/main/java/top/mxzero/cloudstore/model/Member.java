package top.mxzero.cloudstore.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * * @author zero
 * * @email qianmeng6879@163.com
 * * @since 2022/9/16
 */
@Data
public class Member {
    @TableId(type = IdType.INPUT)
    private Long id;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String mobile;
    private String avatar;
    private String salt;
    private Date createTime;
    private Date updateTime;
    private Date loginTime;
    @TableLogic
    private Boolean deleted;

}
