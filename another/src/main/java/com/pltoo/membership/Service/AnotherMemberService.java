package com.pltoo.membership.Service;

import com.pltoo.membership.dto.MyPageDTO;
import com.pltoo.membership.entity.MyPageEntity;
import com.pltoo.membership.repository.MyPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnotherMemberService {

    @Autowired
    private MyPageRepository myPageRepository;

    // 이메일 주소를 사용하여 회원 정보를 조회
    public MyPageDTO getMemberByEmail(String memberEmail) {
        MyPageEntity myPageEntity = myPageRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new IllegalArgumentException("No member found with email: " + memberEmail));
        return MyPageDTO.fromEntity(myPageEntity);
    }
}
