package top.mxzero.cloudstore.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.mxzero.common.AbstractBaseAction;
import top.mxzero.common.response.RestResponse;
import top.mxzero.common.utils.UserUtil;
import top.mxzero.jwt.annotation.JWTAuthentication;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/17
 */
@RestController
public class FileAttrController extends AbstractBaseAction {
    @Value("${mxzero.file.upload.prefix}")
    private String prefix;

    /**
     * 查看文件属性
     *
     * @param filename 文件名称
     */
    @JWTAuthentication
    @GetMapping("/file/attr")
    public RestResponse<?> fileAttr(@RequestParam("filename") String filename) {
        // 文件夹路径需要以/为前缀
        if (!filename.startsWith("/")) {
            filename = "/" + filename;
        }

        File file = new File(prefix + UserUtil.currentUser().getUsername() + filename);


        if (!file.exists()) {
            return RestResponse.fail("文件不存在").code(404);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("文件大小", file.length());
        map.put("是否可读", file.canRead());
        map.put("是否可写", file.canWrite());
        map.put("最后修改时间", format(new Date(file.lastModified())));
        map.put("类型", file.isDirectory() ? "文件夹" : "文件");
        map.put("文件名称", file.getName());
        return RestResponse.success(map);
    }

}

