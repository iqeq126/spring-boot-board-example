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
    /*
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }*/


}
