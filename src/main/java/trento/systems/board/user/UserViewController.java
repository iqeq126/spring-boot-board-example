package trento.systems.board.user;

import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserViewController {
    private final UserService userService;

    public UserViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(UserDTO request) {
        // userService.save(request);
        return "login";
    }
    @GetMapping("/join")
    public String join(UserDTO request) {
        // userService.save(request);
        return "join";
    }

    @GetMapping("/mypage")
    public String mypage(@RequestParam("username") String username, Model model) {
        UserDTO user = userService.findByUsername(username);
        model.addAttribute("dto", user);
        return "mypage";
    }
    @GetMapping("/update/username")
    public String updateUsernameForm(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("currentUsername", userDetails.getUsername());
        return "updateUsername";
    }

    @PostMapping("/update/username")
    public String updateUsername(UsernameUpdateDTO request, Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        request.setCurrentUsername(userDetails.getUsername());
        try {
            userService.updateUsername(request);
            return "redirect:/mypage?username=" + request.getNewUsername();
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            return "updateUsername";
        }
    }
    @GetMapping("/update/password")
    public String updatePasswordForm() {
        return "updatePassword";
    }

    @PostMapping("/update/password")
    public String updatePassword(UserPasswordUpdateDTO request, Model model, Authentication authentication) {
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            //model.addAttribute("dto", request);
            model.addAttribute("different", "비밀번호가 같지않습니다.");
            return "updatePassword";
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userService.updatePassword(request, userDetails.getUsername());
        return "redirect:/mypage?username=" + userDetails.getUsername();

    }


    @GetMapping("/findPassword")
    public String showFindPasswordPage() {
        return "findPassword";
    }
    @PostMapping("/findPassword")
    public String findPassword(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               Model model) {
        try {
            String status = userService.findPw(new findPwRequestDTO(username, email));
            model.addAttribute("message", "Temporary password has been sent to your email.");
            return "login"; // 임시 비밀번호를 이메일로 전송한 후 로그인 페이지로 리디렉션
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "findPassword"; // 오류가 발생한 경우 다시 비밀번호 찾기 페이지로 리디렉션
        }
    }


}
