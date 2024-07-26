package trento.systems.board.dto;

import lombok.Getter;
import trento.systems.board.domain.Article;
import trento.systems.board.user.User;

@Getter
public class ArticleResponse {

    private final String title;
    private final String content;
    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
