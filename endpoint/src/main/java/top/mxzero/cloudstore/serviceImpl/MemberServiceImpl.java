package top.mxzero.cloudstore.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mxzero.cloudstore.mapper.MemberMapper;
import top.mxzero.cloudstore.model.Member;
import top.mxzero.common.dto.MemberDTO;
import top.mxzero.common.dto.MemberDetailDTO;
import top.mxzero.common.service.MemberService;
import top.mxzero.common.utils.DeepBeanUtils;

import java.util.List;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public MemberDetailDTO get(Long id) {
        Member member = memberMapper.selectById(id);

        if (member == null) {
            return null;
        }

        MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
        BeanUtils.copyProperties(member, memberDetailDTO);
        return memberDetailDTO;
    }

    @Override
    public List<MemberDTO> list() {
        List<Member> members = memberMapper.selectList(null);
        return DeepBeanUtils.copyListProperties(members, MemberDTO::new);
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }

    @Override
    public boolean update(MemberDTO memberDTO) {
        return false;
    }

    @Override
    public boolean changePassword(Long id, MemberDTO memberDTO) {
        return false;
    }

    @Override
    public boolean bindOrChangeEmail(MemberDTO memberDTO) {
        return false;
    }

    @Override
    public boolean bindOrChangeMobile(MemberDTO memberDTO) {
        return false;
    }
}
