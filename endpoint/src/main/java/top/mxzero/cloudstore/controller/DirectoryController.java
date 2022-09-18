package top.mxzero.cloudstore.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.mxzero.common.dto.MemberDTO;
import top.mxzero.common.response.RestResponse;
import top.mxzero.common.utils.UserUtil;
import top.mxzero.jwt.annotation.JWTAuthentication;

import java.io.File;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/18
 */
@RestController
public class DirectoryController {
    @Value("${mxzero.file.upload.prefix}")
    private String prefix;

    /**
     * 创建文件夹
     *
     * @param dirname 文件夹路径
     */
    @JWTAuthentication
    @PostMapping("/directory/create")
    public RestResponse<?> createDirectory(@RequestParam("dirname") String dirname) {

        // 文件夹路径需要以/为前缀
        if (!dirname.startsWith("/")) {
            dirname = "/" + dirname;
        }


        MemberDTO memberDTO = UserUtil.currentUser();


        File directory = new File(prefix + memberDTO.getUsername() + dirname);
        if (directory.exists()){
            return RestResponse.fail("文件夹已存在");
        }

        directory.mkdirs();

        return RestResponse.success("文件夹创建成功").code(201);

    }
}
