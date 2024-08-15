package trento.systems.board.user;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MailSender mailSender;

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

    public String findPw(findPwRequestDTO request) throws Exception {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() ->
                new BadCredentialsException("Invalid Account Information"));

        if(!user.getEmail().equals(request.getEmail())) {
            throw new BadCredentialsException("Email does not match");
        }

        String temporaryPassword = generateTemporaryPassword();

        sendTemporaryPasswordEmail(user.getEmail(), temporaryPassword);

        user.updatePassword(bCryptPasswordEncoder.encode(temporaryPassword));
        userRepository.save(user);
        return "Temporary Password Sent";
    }

    private String generateTemporaryPassword() {
        char[] charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder tmpPw = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int idx = (int) (charSet.length * Math.random());
            tmpPw.append(charSet[idx]);
        }
        return tmpPw.toString();
    }

    private void sendTemporaryPasswordEmail(String email, String temporaryPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your Temporary Password");
        message.setText("Your temporary password is: " + temporaryPassword + "\nPlease log in and change your password immediately.");
        mailSender.send(message);
    }


}
