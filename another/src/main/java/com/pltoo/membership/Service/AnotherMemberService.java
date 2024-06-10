package com.pltoo.membership.Service;

import com.pltoo.membership.dto.MemberDTO;
import com.pltoo.membership.entity.MemberEntity;
import com.pltoo.membership.entity.GameEntity;
import com.pltoo.membership.repository.MemberRepository;
import com.pltoo.membership.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnotherMemberService {
    private final MemberRepository memberRepository;
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    // 프로필 정보 가져오기
    public MemberDTO getAnotherMemberProfile(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("No member found with ID: " + memberId));
        return modelMapper.map(memberEntity, MemberDTO.class);
    }

    // 하는게임 목록 가져오기. memberId로 회원을 찾고 해당 회원의 게임목록을 반환.
    // 게임 목록 설정을 마이프로필에서 한다면 아래와 같이 설정.
    // 하지만 지금은 마이페이지가 없으므로 memberEntity에 메서드 구현.
    // MyPage가 구현되면 삭제 후 이동 예정.
    /* private List<GameEntity> games;

    public List<GameEntity> getGames() {
        return this.games;
    }
} */
    public List<GameEntity> getGameListById(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("No member found with ID: " + memberId));
        return memberEntity.getGames();
    }

    // 프로필 이미지 가져오기
    public String getProfileImageById(Long memberId) {
        // 여기에 프로필 이미지를 가져오는 로직을 구현
        // 회원 ID를 이용하여 회원 정보 조회
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("No member found with ID: " + memberId));

        // 회원 정보에서 프로필 이미지 경로 가져오기
        return member.getProfileImage();
    }

    public void addFriend(Long currentUserId, Long friendId) {
        // 현재 사용자와 친구의 ID를 사용하여 친구 추가 로직을 수행합니다.
        // 이 부분은 실제로는 데이터베이스에 친구 관계를 저장하는 등의 작업을 수행해야 합니다.
        // 여기서는 예시로 출력만 합니다.
        System.out.println("사용자 " + currentUserId + "가 사용자 " + friendId + "를 친구로 추가했습니다.");
    }
}
