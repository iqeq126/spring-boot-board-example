package trento.systems.board.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import trento.systems.board.domain.Article;
import trento.systems.board.dto.ArticleViewResponse;
import trento.systems.board.user.UserService;

import java.util.List;

@Controller
public class UserViewController {
    //private final UserService userService;

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
    /*
    @GetMapping("/mypage")
    public String mypage(Model model, String name){
        User user = userService.findByUsername(name);
        model.addAttribute("mypage", user);
        return "mypage";
    }*/

}
