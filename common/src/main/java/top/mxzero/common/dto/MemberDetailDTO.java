package top.mxzero.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@Data
public class MemberDetailDTO {
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String mobile;
    private String avatar;
    private Date createTime;
    private Date loginTime;
}
