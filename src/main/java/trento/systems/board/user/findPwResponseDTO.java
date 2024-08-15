package trento.systems.board.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class findPwResponseDTO {

    private String receiverEmail;
    private String mailTitle;
    private String mailContent;
}