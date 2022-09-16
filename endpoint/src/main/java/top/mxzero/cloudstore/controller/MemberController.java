package top.mxzero.cloudstore.controller;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mxzero.cloudstore.serviceImpl.MemberServiceImpl;
import top.mxzero.common.dto.MemberDTO;
import top.mxzero.common.dto.MemberDetailDTO;
import top.mxzero.common.exception.APIException;
import top.mxzero.common.response.RestResponse;
import top.mxzero.common.utils.UserUtil;
import top.mxzero.jwt.annotation.JWTAuthentication;
import top.mxzero.jwt.service.ITokenService;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@RestController
public class MemberController {
    @Autowired
    private ITokenService tokenService;

    @Autowired
    private MemberServiceImpl memberService;

    @RequestMapping("/authorize")
    public RestResponse authorize(@RequestHeader("Authorization") String token) {
        Jws<Claims> claimsJws = tokenService.parseToken(token.substring(7));

        if (claimsJws == null) {
            return RestResponse.fail("Unauthenticated").code(401);
        }

        String subject = claimsJws.getBody().getSubject();
        MemberDTO memberDTO = JSONObject.parseObject(subject, MemberDTO.class);
        return RestResponse.success(memberDTO);
    }

    @JWTAuthentication
    @RequestMapping("/me")
    public RestResponse<?> currentUser() {
        MemberDTO memberDTO = UserUtil.currentUser();
        MemberDetailDTO memberDetailDTO = memberService.get(memberDTO.getId());
        return RestResponse.success(memberDetailDTO);
    }

    @GetMapping("/members")
    public RestResponse<?> listUser() {
        return RestResponse.success(memberService.list());
    }

    @GetMapping("/members/{id}")
    public RestResponse<?> getOne(@PathVariable("id") Long id) {
        MemberDetailDTO memberDetailDTO = memberService.get(id);
        if (memberDetailDTO == null) {
            RestResponse.fail("Not Found").code(404);
        }
        return RestResponse.success(memberDetailDTO);
    }
}

