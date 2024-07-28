/*package trento.systems.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import trento.systems.board.dto.CommentRequest;
import trento.systems.board.service.CommentService;
import trento.systems.board.user.UserDTO;
import trento.systems.board.user.UserDetailService;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/articles/{id}/comment")
    public String writeComment(@PathVariable Long id, CommentRequest commentRequest, Authentication authentication) {
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();
        commentService.writeComment(commentRequest, id, userDTO.getUsername());
        return "redirect:/articles/" + id;
    }


    @ResponseBody
    @PostMapping("/articles/{id}/comment/{commentId}/update")
    public String updateComment(@PathVariable Long id, @PathVariable Long commentId, CommentRequest commentRequest) {
        commentService.updateComment(commentRequest, commentId);
        return "/articles/" + id;
    }


    @GetMapping("/articles/{id}/comment/{commentId}/remove")
    public String deleteComment(@PathVariable Long id, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/articles/" + id;
    }
}*/

package trento.systems.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import trento.systems.board.dto.CommentRequest;
import trento.systems.board.service.CommentService;
import trento.systems.board.user.UserDTO;
import trento.systems.board.user.UserService;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    /**
     * 댓글 작성
     * @param id 게시물
     * @param commentRequest 댓글 정보
     * @param authentication 유저 정보
     * @return 게시물 상세 페이지
     */
    // @PostMapping("/articles/{id}/comment")
    @RequestMapping(value = "/articles/{id}/comment", method = RequestMethod.POST)
    public String writeComment(@PathVariable Long id, CommentRequest commentRequest, Authentication authentication) {
        UserDetails userdetails = (UserDetails) authentication.getPrincipal();
        commentService.writeComment(commentRequest, id, userdetails.getUsername());//userService.findById(Long.valueOf((userdetails.getUsername()))).getUsername());
        // System.out.println("getUsername() : " + userdetails.getUsername());
        return "redirect:/articles/" + id;
    }

    /**
     * 댓글 수정
     * @param id 게시물
     * @param commentId 댓글 ID
     * @param commentRequest 댓글 정보
     * @return 게시물 상세 페이지
     */
    @ResponseBody
    // @PostMapping("/articles/{id}/comment/{commentId}/update")
    @PutMapping(value = "/articles/{id}/comment/{commentId}/update")
    public String updateComment(@PathVariable Long id, @PathVariable Long commentId, @RequestBody CommentRequest commentRequest) {
        commentService.updateComment(commentRequest, commentId);
        return "/articles/" + id;
    }

    /**
     * 댓글 삭제
     * @param id 게시물
     * @param commentId 댓글 ID
     * @return 해당 게시물 리다이렉트
     */
    @GetMapping("/articles/{id}/comment/{commentId}/remove")
    public String deleteComment(@PathVariable Long id, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/articles/" + id;
    }
}
