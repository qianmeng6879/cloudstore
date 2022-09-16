package top.mxzero.cloudstore.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mxzero.cloudstore.mapper.MemberMapper;
import top.mxzero.cloudstore.mapper.OAuth2QQInfoMapper;
import top.mxzero.cloudstore.model.Member;
import top.mxzero.cloudstore.model.OAuth2QQInfo;
import top.mxzero.cloudstore.prop.OAuthInfoQQProperties;
import top.mxzero.common.dto.MemberDTO;
import top.mxzero.common.exception.APIException;
import top.mxzero.common.service.OAuthLoginService;
import top.mxzero.common.utils.PasswordUtil;
import top.mxzero.common.utils.QQOAuthUtil;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@Service
@Slf4j
public class OAuthLoginServiceImpl implements OAuthLoginService {
    @Autowired
    private OAuthInfoQQProperties qqProperties;

    @Autowired
    private OAuth2QQInfoMapper oAuth2QQInfoMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    @Transactional
    public MemberDTO qqLogin(String code) {
        String accessToken = QQOAuthUtil.getAccessToken(
                qqProperties.getAccessTokenUrl(),
                code,
                qqProperties.getClientKey(),
                qqProperties.getClientSecret(),
                qqProperties.getRedirectUrl()
        );

        if (accessToken == null) {
            throw new APIException("QQ登录失败");
        }

        String openid = QQOAuthUtil.getOpenid(qqProperties.getOpenidUrl(), accessToken);
        OAuth2QQInfo oAuth2QQInfo = oAuth2QQInfoMapper.selectOne(new QueryWrapper<OAuth2QQInfo>().eq("openid", openid));
        Member member = null;
        // 该QQ用户未注册时，新建用户，关联openid
        if (oAuth2QQInfo == null) {
            // 获取QQ用户信息
            Map<String, String> userInfo = QQOAuthUtil.getUserInfo(qqProperties.getUserinfoUrl(), accessToken, openid, qqProperties.getClientKey());

            Date currentTime = new Date(System.currentTimeMillis());

            member = new Member();
            member.setId(IdWorker.getId());
            member.setUsername("u_" + member.getId());
            member.setNickname(userInfo.get("nickname"));
            member.setAvatar(userInfo.get("figureurl_2"));
            member.setCreateTime(currentTime);
            member.setLoginTime(currentTime);

            member.setSalt(UUID.randomUUID().toString());
            member.setPassword(PasswordUtil.hashPassword("0", member.getSalt()));

            // 新增用户信息
            memberMapper.insert(member);

            OAuth2QQInfo qqInfo = new OAuth2QQInfo();
            qqInfo.setId(IdWorker.getId());
            qqInfo.setMemberId(member.getId());
            qqInfo.setOpenid(openid);

            // 新增openid关联
            oAuth2QQInfoMapper.insert(qqInfo);

        } else {
            Long memberId = oAuth2QQInfo.getMemberId();
            member = memberMapper.selectById(memberId);
        }


        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(member, memberDTO);

        return memberDTO;
    }

    @Override
    public MemberDTO weiboLogin(String code) {
        return null;
    }


}
