package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    List<User> getlstUser();
    User getUser(Long userId);
    User updateUser(Long userId, User user);
    boolean deleteUser(Long userId);
}
