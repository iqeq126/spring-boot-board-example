package trento.systems.board.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddUserRequest {
    private String username;
    private String password;
}
