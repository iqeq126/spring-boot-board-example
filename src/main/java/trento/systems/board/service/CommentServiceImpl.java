package trento.systems.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import trento.systems.board.domain.Article;
import trento.systems.board.domain.Comment;
import trento.systems.board.dto.CommentRequest;
import trento.systems.board.dto.CommentResponse;
import trento.systems.board.repository.BlogRepository;
import trento.systems.board.repository.CommentRepository;
import trento.systems.board.user.User;
import trento.systems.board.user.UserRepository;
import trento.systems.board.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;

    @Override
    public Long writeComment(CommentRequest commentRequest, Long articleId, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다."));
        Article article = blogRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
        Comment result = Comment.builder()
                .content(commentRequest.getContent())
                .article(article)
                .user(user)
                .commentWriter(userService.findById(user.getId()).getUsername())
                .build();
        commentRepository.save(result);

        return result.getId();
    }

    @Override
    public List<CommentResponse> commentList(Long id) {
        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
        List<Comment> comments = commentRepository.findByArticle(article);

        return comments.stream()
                .map(comment -> CommentResponse.builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .commentWriter(comment.getCommentWriter())
                        .createdAt(comment.getCreatedDate())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void updateComment(CommentRequest commentRequest, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        comment.update(commentRequest.getContent());
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}