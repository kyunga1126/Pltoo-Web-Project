package repository;

import entity.MyPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPageRepository extends JpaRepository<MyPage, Integer> {
// 이제 MyPage 테이블에 데이터를 저장, 조회, 수정, 삭제 가능
}
