package top.mxzero.common.service;

import top.mxzero.common.dto.MemberDTO;
import top.mxzero.common.dto.MemberDetailDTO;

import java.util.List;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/16
 */
public interface MemberService {

    MemberDetailDTO get(Long id);

    List<MemberDTO> list();

    boolean remove(Long id);

    boolean update(MemberDTO memberDTO);

    boolean changePassword(Long id, MemberDTO memberDTO);

    boolean bindOrChangeEmail(MemberDTO memberDTO);

    boolean bindOrChangeMobile(MemberDTO memberDTO);
}
