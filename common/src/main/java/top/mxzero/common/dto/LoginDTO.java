package top.mxzero.common.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@Data
public class LoginDTO {
    @NotBlank(message = "邮箱为空")
    @Email(message = "邮箱格式错误")
    private String email;

    @NotBlank(message = "密码为空")
    @Length(min = 4, max = 16, message = "密码为4到16位字符")
    private String password;
}
