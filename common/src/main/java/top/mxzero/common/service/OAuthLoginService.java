package top.mxzero.common.service;


import top.mxzero.common.dto.MemberDTO;


/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
public interface OAuthLoginService {
    /**
     * QQ oauth2业务
     * 使用code获取access_token
     * 使用access_token获取openid
     * 判断openid是否数据库
     * 存在则获取关联的用户信息
     * 否则获取qq用户信息,创建新用户,openid关联用户
     *
     * @param code QQ回传code
     * @return
     */
    public MemberDTO qqLogin(String code);

    /**
     * 微博 oauth2业务
     *
     * @param code 微博回传code
     * @return
     */
    public MemberDTO weiboLogin(String code);
}
