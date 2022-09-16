package top.mxzero.common.service;

import top.mxzero.common.dto.MemberDTO;
import top.mxzero.common.dto.RegisterDTO;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
public interface RegisterService {
    public MemberDTO register(RegisterDTO registerDTO);
}
