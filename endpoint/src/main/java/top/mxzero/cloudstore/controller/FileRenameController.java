package top.mxzero.cloudstore.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PutMapping;
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
@RestController
public class FileRenameController {
    @Value("${mxzero.file.upload.prefix}")
    private String prefix;

    /**
     * 文件、文件夹名称修改
     *
     * @param filename 原名称，包含文件路径前缀
     * @param newname  新的文件名称
     */
    @JWTAuthentication
    @PutMapping("/file/rename")
    public RestResponse<?> fileRename(
            @RequestParam(value = "filename") String filename,
            @RequestParam(value = "newname") String newname
    ) {
        // 文件夹路径需要以/为前缀
        if (!filename.startsWith("/")) {
            filename = "/" + filename;
        }

        File file = new File(prefix + UserUtil.currentUser().getUsername() + filename);
        if (!file.exists()) {
            return RestResponse.fail("文件不存在").code(404);
        }

        file.renameTo(new File(file.getParentFile().getAbsolutePath() + File.separatorChar + newname));

        return RestResponse.success(null).message("文件名称修改成功");

    }
}
