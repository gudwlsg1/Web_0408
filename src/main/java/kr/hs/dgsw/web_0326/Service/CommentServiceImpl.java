package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.Comment;
import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_0326.Repository.CommentRepository;
import kr.hs.dgsw.web_0326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init(){
        User user = this.userRepository.save(new User("aaa","aaa@dgsw.hs.kr","1234","D:/school/3Grade/web/web_0326/upload/2019/04/13/e5057064-61e5-4706-9767-99670cf58863_3.jpg","3.jpg"));
        this.commentRepository.save(new Comment(user.getId(),"Hi there 인사해 호들갑 없이"));
        this.commentRepository.save(new Comment(user.getId(),"시작해요 서론 없이"));
        this.commentRepository.save(new Comment(user.getId(),"스킨십은 사양할게요"));
    }

    @Override
    public List<CommentUsernameProtocol> lstAllComments() {
        List<Comment> lstComment = commentRepository.findAll();
        List<CommentUsernameProtocol> lstCommentUsernameProtocol = new ArrayList<>();

        lstComment.forEach(comment -> {
            Optional<User> found = this.userRepository.findById(comment.getUserId());
            String username = found.isPresent() ? found.get().getName() : null;
            lstCommentUsernameProtocol.add(new CommentUsernameProtocol(comment, username));
        });

        return lstCommentUsernameProtocol;
    }

    @Override
    public List<CommentUsernameProtocol> getComment(Long id) {
       Optional<User> found = this.userRepository.findById(id);
       String userName = found.isPresent() ? found.get().getName() : null;

       List<Comment> lstComment = this.commentRepository.findAll();
       List<CommentUsernameProtocol> lstCommentUsernameProtocol = new ArrayList<>();

       lstComment.forEach(c -> {
           if(c.getUserId() == id){
               lstCommentUsernameProtocol.add(new CommentUsernameProtocol(c, userName));
           }
       });

       return  lstCommentUsernameProtocol;
    }

    @Override
    public CommentUsernameProtocol addComment(Comment comment) {

        return this.userRepository.findById(comment.getUserId())
                .map(found -> {
                    this.commentRepository.save(comment);
                    return new CommentUsernameProtocol(comment, found.getName());
                })
                .orElse(null);
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        return this.commentRepository.findById(id)
                .map( f -> {
                            f.setContent(Optional.ofNullable(comment.getContent()).orElse(f.getContent()));
                            f.setStroedPath(Optional.ofNullable(comment.getStroedPath()).orElse(f.getStroedPath()));
                            f.setOriginalName(Optional.ofNullable(comment.getOriginalName()).orElse(f.getOriginalName()));
                            return this.commentRepository.save(f);
                        }).orElse(null);
    }

    @Override
    public boolean deleteComment(Long Id) {
        try {
            this.commentRepository.deleteById(Id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
