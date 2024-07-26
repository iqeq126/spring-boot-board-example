package trento.systems.board.repository;

import trento.systems.board.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import trento.systems.board.user.User;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByOrderByIdDesc();
}

