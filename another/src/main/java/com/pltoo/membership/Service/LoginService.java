package com.pltoo.membership.Service;

import com.pltoo.membership.dto.MemberDTO;
import com.pltoo.membership.entity.MemberEntity;
import com.pltoo.membership.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    public MemberDTO login(MemberDTO memberDTO) {

        /*
            1. 회원이 입력한 이메일로 db에서 조회를 함
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
        */

        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(byMemberEmail.isPresent()){
            //조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다.)
            MemberEntity memberEntity = byMemberEmail.get();
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                // 비밀번호 일치
                //entity -> dto 변환 후 리턴
                MemberDTO dto = MemberDTO.fromEntity(memberEntity);
                return dto;
            }else{
                //비밀번호 불일치
                return null;
            }
        }else{
            //조회 결과가 없다.(해당 이메일을 가진 회원이 없다.)
            return null;
        }
    }
}
