package com.pltoo.membership.Service;

import com.pltoo.membership.dto.MemberDTO;
import com.pltoo.membership.entity.MemberEntity;
import com.pltoo.membership.entity.MyPageEntity;
import com.pltoo.membership.repository.MemberRepository;
import com.pltoo.membership.repository.MyPageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MyPageRepository myPageRepository;
    //ModelMapper
    private final ModelMapper modelMapper = new ModelMapper();  // ModelMapper 인스턴스 생성

    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        //2. repository의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        //repository의 save메서드 호출(조건. entity객체를 넘겨줘야 함)
    }

    public Long findIdByEmail(String MemberEmail) {
        Long memberNum = memberRepository.findIdByEmail(MemberEmail);
        if (memberNum != null) {
            return memberNum;
        }
        return null;
    }

    public void updateMemberNickname(String memberEmail, String memberNickname) {
        MemberEntity member = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new IllegalArgumentException("No member found with email: " + memberEmail));
        member.setNickname(memberNickname);
        memberRepository.save(member);
    }

    public boolean emailExists(String memberEmail) {
        return memberRepository.existsByMemberEmail(memberEmail);
    }

    public void updatePno(String loginEmail, String memberPno) {
        MemberEntity member = memberRepository.findByMemberEmail(loginEmail)
                .orElseThrow(() -> new IllegalArgumentException("No member found with email: " + loginEmail));
        member.setMemberPno(memberPno);
        memberRepository.save(member);
    }


}
