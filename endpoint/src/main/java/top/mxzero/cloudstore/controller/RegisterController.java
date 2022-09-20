package top.mxzero.cloudstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.mxzero.common.dto.MemberDTO;
import top.mxzero.common.response.RestResponse;
import top.mxzero.common.service.RegisterService;
import top.mxzero.common.AbstractBaseAction;
import top.mxzero.common.dto.RegisterDTO;
import top.mxzero.jwt.service.ITokenService;

import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@RestController
public class RegisterController extends AbstractBaseAction {

    @Value("${mxzero.file.upload.prefix}")
    private String prefix;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private ITokenService tokenService;


    @PostMapping("/register")
    public RestResponse register(@Valid @RequestBody RegisterDTO registerDTO) {
        MemberDTO memberDTO = registerService.register(registerDTO);

        Map<String, Object> map = new HashMap<>();
        map.put("id", memberDTO.getId());
        map.put("nickname", memberDTO.getNickname());
        map.put("avatar", memberDTO.getAvatar());
        map.put("createTime", format(memberDTO.getCreateTime()));
        String token = tokenService.createToken(UUID.randomUUID().toString(), map);

        File directory = new File(prefix + memberDTO.getUsername());

        if(!directory.exists()){
            try {
                directory.mkdirs();
            }catch (Exception ignored){}
        }

        return RestResponse.success(token).message("注册成功").code(201);
    }
}
