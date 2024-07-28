package trento.systems.board.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import trento.systems.board.domain.Article;
import trento.systems.board.domain.Comment;
import trento.systems.board.dto.ArticleViewResponse;
import trento.systems.board.dto.CommentResponse;
import trento.systems.board.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import trento.systems.board.service.CommentService;

import java.util.List;
@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;
    private final CommentService commentService;
    @GetMapping("/articles")
    public String getArticles(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size) {
        List<ArticleViewResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);
        Page<Article> articlePage = blogService.findPaginated(PageRequest.of(page, size));
        model.addAttribute("articlePage", articlePage);
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        blogService.getViewCount(id);
        blogService.getCommentCount(id);
        List<CommentResponse> comments = commentService.commentList(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        model.addAttribute("comments", comments);
        System.out.println("comments : " + comments);
        model.addAttribute("id", id);
        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
}