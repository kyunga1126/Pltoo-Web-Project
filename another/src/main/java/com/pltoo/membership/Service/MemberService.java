package com.pltoo.membership.Service;

import com.pltoo.membership.dto.GameDTO;
import com.pltoo.membership.dto.MemberDTO;
import com.pltoo.membership.entity.GameEntity;
import com.pltoo.membership.entity.MemberEntity;
import com.pltoo.membership.repository.GameRepository;
import com.pltoo.membership.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public MemberDTO findMemberById(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("No member found with ID: " + memberId));
        return modelMapper.map(memberEntity, MemberDTO.class);
    }

    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
        MemberEntity memberEntity = modelMapper.map(memberDTO, MemberEntity.class);
        memberRepository.save(memberEntity);
        // repository의 save메서드 호출(조건. entity객체를 넘겨줘야 함)
    }

    public Long findIdByEmail(String memberEmail) {
        return memberRepository.findIdByEmail(memberEmail);
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
}
