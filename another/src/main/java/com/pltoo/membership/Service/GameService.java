package com.pltoo.membership.Service;

import com.pltoo.membership.dto.GameDTO;
import com.pltoo.membership.entity.GameEntity;
import com.pltoo.membership.entity.MemberEntity;
import com.pltoo.membership.repository.GameRepository;
import com.pltoo.membership.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    //ModelMapper
    public GameEntity createGame(GameDTO gameDTO) {
        MemberEntity member = memberRepository.findById(gameDTO.getMemberNum())
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
        GameEntity gameEntity = GameEntity.toGameEntity(gameDTO, member);
        return gameRepository.save(gameEntity);
    }
}
