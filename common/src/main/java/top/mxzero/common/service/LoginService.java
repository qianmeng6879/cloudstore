package top.mxzero.common.service;

import top.mxzero.common.dto.LoginDTO;
import top.mxzero.common.dto.MemberDTO;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
public interface LoginService {
    public MemberDTO login(LoginDTO loginDTO);
}
