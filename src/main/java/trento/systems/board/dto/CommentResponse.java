package trento.systems.board.dto;


import lombok.*;
import trento.systems.board.domain.Comment;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long id;
    private String content;
    private String username;
    private String commentWriter;
    private String email;
    // private String imageUrl;


    @Builder
    public CommentResponse(Comment comment) {
        this.createdAt = comment.getCreatedDate();
        this.updatedAt = comment.getLastModifiedDate();
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUser().getUsername();
        this.commentWriter= comment.getCommentWriter();
        this.email = comment.getUser().getEmail();
        // this.imageUrl = comment.getMember().getImage().getUrl();
    }
}
