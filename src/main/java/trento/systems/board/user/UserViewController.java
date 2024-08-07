package trento.systems.board.user;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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

}
