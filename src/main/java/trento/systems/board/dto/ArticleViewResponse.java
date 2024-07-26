package trento.systems.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import trento.systems.board.domain.Article;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
public class ArticleViewResponse {

  private Long id;
  private String title;
  private String content;
  private String writer;
  private LocalDateTime createdAt;
  private int countView;
  public ArticleViewResponse(Article article) {
    this.id = article.getId();
    this.title = article.getTitle();
    this.content = article.getContent();
    this.writer = article.getWriter();
    this.createdAt = article.getCreatedAt();
    this.countView = article.getCountView();
  }
}
