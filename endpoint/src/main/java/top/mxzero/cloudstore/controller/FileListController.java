package top.mxzero.cloudstore.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.mxzero.common.response.RestResponse;
import top.mxzero.common.utils.UserUtil;
import top.mxzero.jwt.annotation.JWTAuthentication;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/17
 */
@RestController
public class FileListController {
    @Value("${mxzero.file.upload.prefix}")
    private String prefix;

    /**
     * 文件列表接口口
     *
     * @param dirname 文件夹名称
     */
    @JWTAuthentication
    @GetMapping("/file/list")
    public RestResponse<?> fileList(@RequestParam(value = "dirname", defaultValue = "/") String dirname) {
        // 文件夹路径需要以/为前缀
        if (!dirname.startsWith("/")) {
            dirname = "/" + dirname;
        }

        // 文件夹后缀
        if (!dirname.endsWith("/")) {
            dirname = dirname + "/";
        }

        String username = UserUtil.currentUser().getUsername();

        File file = new File(prefix + username + dirname);

        if (!file.exists()) {
            return RestResponse.fail("文件路径不存在").code(404);
        }

        if (!file.isDirectory()) {
            return RestResponse.fail("该路径不是文件夹");
        }

        File[] files = file.listFiles();

        List<String> filenames = new LinkedList<>();

        for (int i = 0; i < files.length; i++) {
            filenames.add(files[i].getName());
        }

        return RestResponse.success(filenames);
    }
}
