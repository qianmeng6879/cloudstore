package top.mxzero.cloudstore.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@Data
@TableName("oauth2_qq_info")
public class OAuth2QQInfo {
    @TableId(type = IdType.INPUT)
    private Long id;
    private String openid;
    private Long memberId;
}
