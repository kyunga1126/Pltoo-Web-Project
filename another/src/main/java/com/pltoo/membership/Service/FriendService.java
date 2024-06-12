package com.pltoo.membership.Service;

import com.pltoo.membership.entity.FriendEntity;
import com.pltoo.membership.entity.MemberEntity;
import com.pltoo.membership.repository.FriendRepository;
import com.pltoo.membership.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private FriendRepository friendRepository;
    @Transactional
    //친구 추가 요청 처리, 새로운 친구관계 DB 저장
    public void addFriend(Long userId, Long friendId) {
        // 사용자와 친구 엔티티 조회
        MemberEntity user = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        MemberEntity friend = memberRepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid friend ID"));

        FriendEntity friendEntity = new FriendEntity();
        friendEntity.setUser(user);
        friendEntity.setFriend(friend);

        //DB에 저장
        friendRepository.save(friendEntity);
    }

    // 사용자의 친구 목록을 조회
    public List<FriendEntity> getFriends(Long userId) {
        // 사용자의 엔티티 조회
        MemberEntity user = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        //친구 목록을 조회
        return friendRepository.findByUser(user);
    }

    // 해당하는 사용자의 친구목록에서 친구들의 닉네임을 조회
    public List<String> getFriendNicknames(Long userId) {
        List<FriendEntity> friends = getFriends(userId);

        // stream을 사용해서 친구의 닉네임을 추출한 후 리스트로 변환
        return friends.stream()
                .map(friend -> friend.getFriend().getNickname())
                .collect(Collectors.toList());
    }
}
