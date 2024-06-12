package com.pltoo.membership.Service;

import com.pltoo.membership.dto.MyPageDTO;
import com.pltoo.membership.entity.MemberEntity;
import com.pltoo.membership.entity.MyPageEntity;
import com.pltoo.membership.repository.MemberRepository;
import com.pltoo.membership.repository.MyPageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MyPageRepository myPageRepository;
    private final MemberRepository memberRepository;

    // MyPageDTO를 받아 MyPageEntity를 저장하는 메서드
    public void save(MyPageDTO myPageDTO) {
        MemberEntity foundMember = memberRepository.findById(myPageDTO.getMemberNum())
                .orElseThrow(() -> new IllegalArgumentException("No member found with ID: " + myPageDTO.getMemberNum()));
        MyPageEntity myPageEntity = MyPageEntity.saveMyPageEntity(myPageDTO, foundMember);
        myPageRepository.save(myPageEntity);
    }

    // 새로운 회원을 등록하는 메서드
//    public void registerNewMember(MyPageDTO myPageDTO) {
//        Long memberNum = myPageDTO.getMemberNum();
//        if (memberNum == null) {
//            log.error("Member number is null for email: {}", myPageDTO.getMemberEmail());
//            throw new IllegalArgumentException("Member number cannot be null");
//        }
//
//        MemberEntity foundMember = memberRepository.findById(memberNum)
//                .orElseThrow(() -> new IllegalArgumentException("No member found with ID: " + memberNum));
//        MyPageEntity myPageEntity = MyPageEntity.saveMyPageEntity(myPageDTO,foundMember);
//        myPageRepository.save(myPageEntity);
//
//        updateEmailAndId(foundMember.getMemberNum(), myPageDTO.getMemberEmail());
//    }

    // 이메일 및 ID를 업데이트하는 메서드
    private void updateEmailAndId(Long memberNum, String memberEmail) {
        MyPageEntity myPageEntity = myPageRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new IllegalArgumentException("No profile found for member with email: " + memberEmail));
        myPageEntity.setMemberEmail(memberEmail);
        myPageRepository.save(myPageEntity);
    }

    // MyPageEntity를 가져오거나 없으면 생성하는 메서드
    public MyPageEntity getOrCreateMyPageEntity(MemberEntity memberEntity) {
        return myPageRepository.findByMemberEmail(memberEntity.getMemberEmail())
                .orElseGet(() -> {
                    MyPageEntity newMyPageEntity = new MyPageEntity();
                    newMyPageEntity.setMemberEmail(memberEntity.getMemberEmail());
                    newMyPageEntity.setMemberEntity(memberEntity); // MemberEntity와의 관계 설정
                    return myPageRepository.save(newMyPageEntity);
                });
    }
    // MyPageDTO를 받아 MyPageEntity를 저장하거나 업데이트하는 메서드
    public void saveOrUpdateMyPage(MyPageDTO myPageDTO, String memberEmail) {
        MemberEntity foundMember = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new IllegalStateException("No member found with email: " + memberEmail));

        MyPageEntity myPageEntity = myPageRepository.findByMemberEmail(memberEmail)
                .map(entity -> MyPageEntity.updateMyPageEntity(entity, myPageDTO))
                .orElseGet(() -> MyPageEntity.saveMyPageEntity(myPageDTO, foundMember));

        myPageRepository.save(myPageEntity);
    }
}