package trento.systems.board.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.webjars.NotFoundException;
import trento.systems.board.domain.Article;
import trento.systems.board.dto.AddArticleRequest;
import trento.systems.board.dto.ArticleListViewResponse;
import trento.systems.board.dto.ArticleViewResponse;
import trento.systems.board.dto.UpdateArticleRequest;
import trento.systems.board.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import trento.systems.board.user.User;
import trento.systems.board.user.UserRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    @Transactional
    public Article save(@RequestBody AddArticleRequest dto) {
        //return dto.toEntity();
        return blogRepository.save(dto.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }

    public Article getViewCount(long id){
        Optional<Article> article = blogRepository.findById(id);

        if(article.isPresent()){
            //조회수
            Article article1 = article.get();
            article1.setCountView(article1.getCountView() + 1);
            blogRepository.save(article1);
            return article1;
            //조회수끝
        }else{
            throw new NotFoundException("article not found");
        }
    }
    public Page<Article> findPaginated(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }
/*
    public Page<ArticleListViewResponse> boardList(Pageable pageable) {
        Page<Article> boards = blogRepository.findAll(pageable);
        List<ArticleListViewResponse> boardDTOs = new ArrayList<>();
        for ( Article board : boards) {
            ArticleListViewResponse result = new ArticleListViewResponse(board);
            boardDTOs.add(result);
        }
        return new PageImpl<>(boardDTOs, pageable, boards.getTotalElements());
    }
*/
}
