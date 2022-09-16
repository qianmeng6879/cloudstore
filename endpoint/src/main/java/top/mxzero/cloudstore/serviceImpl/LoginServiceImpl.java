package top.mxzero.cloudstore.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mxzero.cloudstore.mapper.MemberMapper;
import top.mxzero.cloudstore.model.Member;
import top.mxzero.common.dto.LoginDTO;
import top.mxzero.common.dto.MemberDTO;
import top.mxzero.common.exception.APIException;
import top.mxzero.common.service.LoginService;

import java.util.Date;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public MemberDTO login(LoginDTO loginDTO) {
        Member member = memberMapper.selectOne(new QueryWrapper<Member>().eq("email", loginDTO.getEmail()));
        if (member == null) {
            throw new APIException("账号不存在");
        }

        Date currentDate = new Date(System.currentTimeMillis());

        // 记录用户最近一次登录时间
        memberMapper.changeLoginDate(member.getId(), currentDate);

        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(member, memberDTO);
        memberDTO.setLoginTime(currentDate);

        return memberDTO;
    }
}
