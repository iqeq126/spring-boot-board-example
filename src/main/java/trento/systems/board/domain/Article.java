package trento.systems.board.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import trento.systems.board.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter
@Table(name = "article")
@AllArgsConstructor(access=AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    ///@ManyToOne(fetch = FetchType.LAZY) // Many = Board, User = One 한명의 유저는 여러개의 게시글을 쓸 수 있다.
    //JoinColumn(name="user") // foreign key (userId) references User (id)
    //private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다. //참조 할 테이블

    @Column(name = "title", nullable = false, length=60)
    private String title;

    //@Column(name = "writer", nullable = false)
    //private String writer;
    @Column(name="count_view", columnDefinition = "integer default 0", nullable = false)
    private int countView; /*조회수*/

    @Column(name = "content", nullable = false, length = 1500)
    private String content;

    /*
    @Column(name = "file_name", nullable = false)
    private String fileName;//파일이름
    @Column(name = "file_path", nullable = false, length=500)
    private String filePath;//파일경로
    */
    @Column(name = "writer", nullable = false, length = 20)
    private String writer;//작성자

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @OrderBy("id asc")
    @Column(name = "comments")
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="username")
    private User user;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /*@Builder
    public Article( String title, String content) {
        this.title = title;
        this.content = content;
    }*/

    @Builder
    public Article( String writer, String title, String content){
        this.title = title;
        this.content = content;
        this.writer= writer;
    }

    public void update( String title, String content) {
        this.title = title;
        this.content = content;
    }


}
