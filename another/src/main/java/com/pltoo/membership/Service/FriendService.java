package com.pltoo.membership.Service;

import com.pltoo.membership.entity.FriendEntity;
import com.pltoo.membership.entity.MemberEntity;
import com.pltoo.membership.entity.MyPageEntity;
import com.pltoo.membership.repository.FriendRepository;
import com.pltoo.membership.repository.MemberRepository;
import com.pltoo.membership.repository.MyPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final MyPageRepository mypageRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository, MyPageRepository mypageRepository) {
        this.friendRepository = friendRepository;
        this.mypageRepository = mypageRepository;
    }

    // 사용자의 친구목록을 가져오는 메서드
    public List<FriendEntity> getFriends(Long userId) {
        return friendRepository.findByMemberId(userId);
    }

    public void addFriend(Long userId, Long friendId) {
        MyPageEntity user = mypageRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        MyPageEntity friend = mypageRepository.findById(friendId).orElseThrow(() -> new RuntimeException("Friend not found"));

        FriendEntity friendship = new FriendEntity();
        friendship.setMember(user);
        friendship.setFriend(friend);

        friendRepository.save(friendship);
    }
}
