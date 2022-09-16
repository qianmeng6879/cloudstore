package top.mxzero.cloudstore.controller;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.mxzero.common.AbstractBaseAction;
import top.mxzero.common.dto.LoginDTO;
import top.mxzero.common.dto.MemberDTO;
import top.mxzero.common.response.RestResponse;
import top.mxzero.common.service.LoginService;
import top.mxzero.jwt.service.ITokenService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@Valid
@RestController
public class LoginController extends AbstractBaseAction {
    @Autowired
    private LoginService loginService;
    @Autowired
    private ITokenService tokenService;

    @PostMapping("/login")
    public RestResponse<?> loginHandler(@RequestBody LoginDTO loginDTO) {
        MemberDTO memberDTO = loginService.login(loginDTO);

        Map<String, Object> map = new HashMap<>();
        map.put("id", memberDTO.getId());
        map.put("nickname", memberDTO.getNickname());
        map.put("avatar", memberDTO.getAvatar());
        map.put("createTime", format(memberDTO.getCreateTime()));
        map.put("loginTime", format(memberDTO.getLoginTime()));

        String token = tokenService.createToken(UUID.randomUUID().toString(), map);

        return RestResponse.success(token).message("登录成功");
    }
}
