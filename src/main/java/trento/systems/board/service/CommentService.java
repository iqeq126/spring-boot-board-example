package trento.systems.board.service;

import trento.systems.board.dto.CommentRequest;
import trento.systems.board.dto.CommentResponse;

import java.util.List;

public interface CommentService {
    /**
     * 댓글 작성
     *
     * @param commentRequestDTO 댓글 정보
     * @param boardId           게시물
     * @param username             작성자
     * @return 댓글 ID
     */
    Long writeComment(CommentRequest commentRequestDTO, Long boardId, String username);

    /**
     * 댓글 조회
     *
     * @param id 게시물
     * @return 게시물 별 댓글
     */
    List<CommentResponse> commentList(Long id);

    /**
     * 댓글 수정
     *
     * @param commentRequestDTO 댓글 정보
     * @param commentId         댓글 ID
     */
    void updateComment(CommentRequest commentRequestDTO, Long commentId);

    /**
     * 댓글 삭제
     *
     * @param commentId 댓글 ID
     */
    void deleteComment(Long commentId);
}
