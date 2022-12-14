package top.mxzero.cloudstore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mxzero.cloudstore.serviceImpl.MemberServiceImpl;
import top.mxzero.common.dto.MemberDTO;
import top.mxzero.common.dto.MemberDetailDTO;
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

    @JWTAuthentication
    @RequestMapping("/authorize")
    public RestResponse authorize() {
        return RestResponse.success(UserUtil.currentUser());
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

    /**
     * 用户信息修改
     *
     * @param id        用户ID
     * @param memberDTO 用户信息DTO类
     */
    @JWTAuthentication
    @PutMapping("/members/{id}")
    public RestResponse<?> editMemberInfo(@PathVariable("id") Long id, MemberDTO memberDTO) {
        return null;
    }
}

