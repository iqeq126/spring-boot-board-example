package trento.systems.board.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsernameUpdateDTO {

    @NotBlank(message = "기존 이름를 입력해주세요.")
    private String currentUsername;

    @NotBlank(message = "새로운 이름를 입력해주세요.")
    private String newUsername;
}
