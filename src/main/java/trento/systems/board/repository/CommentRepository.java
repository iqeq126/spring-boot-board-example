package trento.systems.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trento.systems.board.domain.Article;
import trento.systems.board.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByArticle(Article article);
}
