package service;

import entity.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.MyPageRepository;

import java.util.Optional;

@Service
public class MyPageService {
    @Autowired
    private MyPageRepository myPageRepository;

    public Optional<MyPage> getMyPage(Integer id) {
        return myPageRepository.findById(id);
    }
}
