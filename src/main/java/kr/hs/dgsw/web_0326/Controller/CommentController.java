package kr.hs.dgsw.web_0326.Controller;

import kr.hs.dgsw.web_0326.Domain.Comment;
import kr.hs.dgsw.web_0326.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_0326.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public List<CommentUsernameProtocol> listComments(){
        return this.commentService.lstAllComments();
    }

    @GetMapping("/list/{userId}")
    public List<CommentUsernameProtocol> listComments(@PathVariable Long userId){
        return this.commentService.getComment(userId);
    }

    @PostMapping("/list")
    public CommentUsernameProtocol listComments(@RequestBody Comment comment){
        return this.commentService.addComment(comment);
    }

    @PutMapping("/list/{Id}")
    public Comment updateComment(@RequestBody Comment comment, @PathVariable Long Id){
        return this.commentService.updateComment(Id,comment);
    }

    @DeleteMapping("/list/{Id}")
    public boolean deleteComment(@PathVariable Long Id){
        return this.commentService.deleteComment(Id);
    }
}
