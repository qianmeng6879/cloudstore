package top.mxzero.cloudstore.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.mxzero.common.response.RestResponse;
import top.mxzero.common.utils.UserUtil;
import top.mxzero.jwt.annotation.JWTAuthentication;

import java.io.File;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/17
 */
@Slf4j
@RestController
public class FileRemoveController {
    @Value("${mxzero.file.upload.prefix}")
    private String prefix;

    /**
     * 文件、文件夹以及子文件删除
     *
     * @param filename 完整的文件路径
     */
    @JWTAuthentication
    @DeleteMapping("/file/remove")
    public RestResponse<?> removeFile(
            @RequestParam(value = "filename", defaultValue = "/") String filename
    ) {
        // 文件夹路径需要以/为前缀
        if (!filename.startsWith("/")) {
            filename = "/" + filename;
        }

        // 是否删除根目录
        if ("/".equals(filename)) {
            return RestResponse.fail("无法删除根目录");
        }

        File file = new File(prefix + UserUtil.currentUser().getUsername() + filename);

        if (!file.exists()) {
            return RestResponse.fail("文件不存在");
        }

        deleteFile(file);
        return RestResponse.success(null).message("删除成功");

    }


    private void deleteFile(File file) {
        // 递归删除文件夹下的子文件
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFile(files[i]);
            }
        }

        file.delete();
    }
}

