package top.mxzero.cloudstore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.mxzero.common.dto.MemberDTO;
import top.mxzero.common.response.RestResponse;
import top.mxzero.common.utils.UserUtil;
import top.mxzero.jwt.annotation.JWTAuthentication;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/17
 */
@Slf4j
@RestController
public class FileUploadController {

    @Value("${mxzero.file.upload.prefix}")
    private String prefix;

    /**
     * 文件上传接口
     *
     * @param file    文件
     * @param dirname 文件保存的文件夹前缀，默认为用户根目录
     */
    @JWTAuthentication
    @PostMapping("/upload")
    public RestResponse<?> upload(MultipartFile file, @RequestParam(value = "dirname", defaultValue = "/") String dirname) throws IOException {
        // 文件夹路径需要以/为前缀
        if (!dirname.startsWith("/")) {
            dirname = "/" + dirname;
        }

        // 文件夹后缀
        if (!dirname.endsWith("/")) {
            dirname = dirname + "/";
        }

        MemberDTO memberDTO = UserUtil.currentUser();

        // 文件保存
        File currentFile = new File(prefix + memberDTO.getUsername() + dirname + file.getOriginalFilename());
        // 父路径不存在时创建路径
        log.info("file:{}", currentFile.getCanonicalPath());
        if (!currentFile.getParentFile().exists()) {
            currentFile.getParentFile().mkdirs();
        }


        // 判断文件名是否存在, 存在则增加数字后最
        int count = 1;
        String filename = currentFile.getName();
        while (currentFile.exists()) {
            if (count >= 3) {
                return RestResponse.fail("请不要上传重复文件");
            }

            String newFilename = String.format("%s(%s).%s", filename.substring(0, filename.lastIndexOf(".")), count++, getFileExtendName(filename));
            currentFile = new File(prefix + memberDTO.getUsername() + dirname + newFilename);
        }
        file.transferTo(currentFile);

        Map<String, String> map = new HashMap<>();
        map.put("username", memberDTO.getUsername());
        map.put("dirname", dirname);
        map.put("filename", currentFile.getName());
        map.put("filefullname", dirname + file.getOriginalFilename());
        map.put("filestorename", currentFile.getCanonicalPath());
        map.put("filesize", String.format("%skB", file.getSize() / 1024));
        map.put("extendname", getFileExtendName(file.getOriginalFilename()));
        return RestResponse.success(map);
    }

    private String getFileExtendName(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
