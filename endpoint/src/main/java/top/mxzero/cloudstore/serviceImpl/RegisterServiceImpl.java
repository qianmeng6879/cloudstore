package top.mxzero.cloudstore.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mxzero.cloudstore.mapper.MemberMapper;
import top.mxzero.cloudstore.model.Member;
import top.mxzero.common.dto.MemberDTO;
import top.mxzero.common.service.RegisterService;
import top.mxzero.common.dto.RegisterDTO;
import top.mxzero.common.exception.APIException;
import top.mxzero.common.utils.PasswordUtil;
import java.util.Date;
import java.util.UUID;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    @Transactional
    public MemberDTO register(RegisterDTO registerDTO) {
        if (memberMapper.exists(new QueryWrapper<Member>().eq("email", registerDTO.getEmail()).eq("deleted", 0))) {
            throw new APIException("邮箱已被注册");
        }

        Member member = new Member();

        member.setId(IdWorker.getId());
        member.setUsername("u_" + member.getId());
        member.setSalt(UUID.randomUUID().toString());

        member.setNickname(registerDTO.getNickname());
        member.setEmail(registerDTO.getEmail());
        member.setPassword(PasswordUtil.hashPassword(registerDTO.getPassword(), member.getSalt()));
        member.setCreateTime(new Date());
        member.setAvatar("member/default.jpg");

        int result = memberMapper.insert(member);

        MemberDTO memberDTO = new MemberDTO();

        BeanUtils.copyProperties(member, memberDTO);

        return result > 0 ? memberDTO : null;
    }
}
