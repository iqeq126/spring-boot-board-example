package trento.systems.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import trento.systems.board.domain.Article;
import trento.systems.board.domain.Comment;
import trento.systems.board.repository.CommentRepository;
import trento.systems.board.user.User;

import java.util.List;

@Getter
@AllArgsConstructor
public class ArticleListViewResponse {

    private final String writer;
    private final Long id;
    private final int countView;
    private final String title;
    private final String content;
    private final int commentSize;
    public ArticleListViewResponse(Article article) {
        this.writer = article.getWriter();
        this.id = article.getId();
        this.countView =  article.getCountView();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.commentSize = article.getCountComment();
    }
}
