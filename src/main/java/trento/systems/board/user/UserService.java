package trento.systems.board.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import trento.systems.board.user.User;
import trento.systems.board.user.UserDTO;
import trento.systems.board.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(@RequestBody UserDTO dto) {
        return userRepository.save(User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }


    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));

        return UserDTO.builder()
                .user(user)
                .build();
    }

    public String updateUsername(UsernameUpdateDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getCurrentUsername())
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
        user.updateUsername(userDTO.getNewUsername());
        userRepository.save(user);
        return user.getUsername();
    }

    public String updatePassword(UserPasswordUpdateDTO userDTO, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
        user.updatePassword(bCryptPasswordEncoder.encode(userDTO.getNewPassword()));
        userRepository.save(user);
        return user.getUsername();
    }

}
