package service;

import entity.Friend;
import entity.Game;
import entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.FriendRepository;
import repository.GameRepository;
import repository.UserProfileRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private FriendRepository friendRepository;

    public Optional<UserProfile> getUserProfile(Integer id) {
        return userProfileRepository.findById(id);
    }

    public List<Friend> getUserFriends(Integer userId) {
        return friendRepository.findByUserProfileId(userId);
    }

    public void addFriend(Integer userId, Integer friendId) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
        if (userProfile.isPresent()) {
            Friend friend = new Friend();
            friend.setUserProfile(userProfile.get());
            friend.setFriendId(friendId);
            friendRepository.save(friend);
        }
    }
}

