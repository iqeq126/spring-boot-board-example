package trento.systems.board.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class findPwRequestDTO {

    private String username;
    private String email;

}
