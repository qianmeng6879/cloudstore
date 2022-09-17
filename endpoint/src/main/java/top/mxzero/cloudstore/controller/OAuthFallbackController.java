package top.mxzero.cloudstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mxzero.cloudstore.model.Member;
import top.mxzero.common.dto.MemberDTO;
import top.mxzero.common.service.OAuthLoginService;
import top.mxzero.common.AbstractBaseAction;
import top.mxzero.jwt.service.ITokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@Controller
@RequestMapping("/oauth2/*")
public class OAuthFallbackController extends AbstractBaseAction {

    @Autowired
    private OAuthLoginService loginService;

    @Autowired
    private ITokenService tokenService;

    @RequestMapping("qq_fallback")
    public void qqFallback(String code, HttpServletResponse response) throws IOException {
        MemberDTO memberDTO = loginService.qqLogin(code);
        Map<String, Object> map = new HashMap<>();
        map.put("id", memberDTO.getId());
        map.put("username", memberDTO.getUsername());
        map.put("nickname", memberDTO.getNickname());
        map.put("avatar", memberDTO.getAvatar());
        map.put("createTime", format(memberDTO.getCreateTime()));
        map.put("loginTime", format(memberDTO.getLoginTime()));

        String token = tokenService.createToken(UUID.randomUUID().toString(), map);

        response.sendRedirect("http://fast.mxzero.top/?token=" + token);

    }
}
