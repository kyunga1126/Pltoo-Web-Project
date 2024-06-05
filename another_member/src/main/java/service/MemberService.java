package service;

import dto.MemberDTO;
import entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) { // 4-4에서 만든 메서드
        // 1. dto -> entity 변환
        //2. repository의 save 메서드 호출
        //5-6에서 MemberEntity 만든 후 사용
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        //repository의 save메서드 호출(조건. entity객체를 넘겨줘야 함)
    }

    public void nickname(MemberDTO memberDTO){
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberEntity.setNickname(memberDTO.getNickname());
        memberRepository.save(memberEntity);
    }
}
