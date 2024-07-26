package trento.systems.board.user;

import lombok.RequiredArgsConstructor;
import trento.systems.board.user.User;
import trento.systems.board.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException((username)));
    }

/*
    public User loadUserByEmail(String email) {
        return userRepository.findByUsername(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }*/
}
