package top.mxzero.common.dto;

import lombok.Data;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/17
 */
@Data
public class FileDTO {
    private Long id;
    private String name;
    private String size;
    private boolean read;
    private boolean write;
    private String type;
    private String lastModified;
}
