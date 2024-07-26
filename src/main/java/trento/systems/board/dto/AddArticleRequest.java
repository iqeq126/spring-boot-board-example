package trento.systems.board.dto;

import lombok.*;
import trento.systems.board.domain.Article;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AddArticleRequest {
    private String title;
    private String content;
    private String writer;
    /*private String fileName;
    private String filePath;*/


    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .writer(writer)
                /*.fileName(fileName)
                .filePath(filePath)*/
                .build();
    }
}
