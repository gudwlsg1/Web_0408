package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.Comment;
import kr.hs.dgsw.web_0326.Protocol.CommentUsernameProtocol;

import java.util.List;

public interface CommentService {
    List<CommentUsernameProtocol> lstAllComments();
    List<CommentUsernameProtocol> getComment(Long userId);
    CommentUsernameProtocol addComment(Comment comment);
    Comment updateComment(Long id, Comment comment);
    boolean deleteComment(Long Id);
}
