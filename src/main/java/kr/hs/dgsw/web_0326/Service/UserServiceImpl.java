package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        Optional<User> found = this.userRepository.findByEmail(user.getEmail());
        if(found.isPresent()){
            return null;
        }

        if(user.getName().equals("") || user.getEmail().equals("")){
            return null;
        }

        return this.userRepository.findByEmail(user.getEmail())
                .orElse(this.userRepository.save(user));
    }

    @Override
    public List<User> getlstUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        return this.userRepository.findById(userId).map(user -> user).orElse(null);
    }

    @Override
    public User updateUser(Long userId, User user) {
       return this.userRepository.findById(userId)
               .map(f -> {
                    f.setName(Optional.ofNullable(user.getName()).orElse(f.getName()));
                    f.setPassword(Optional.ofNullable(user.getPassword()).orElse(f.getPassword()));
                    f.setEmail(Optional.ofNullable(user.getEmail()).orElse(f.getEmail()));
                    f.setStroedPath(Optional.ofNullable(user.getStroedPath()).orElse(f.getStroedPath()));
                    f.setOriginalName(Optional.ofNullable(user.getOriginalName()).orElse(f.getOriginalName()));
                    return this.userRepository.save(f);
                })
                .orElse(null);
    }

    @Override
    public boolean deleteUser(Long userId) {
        try {
            this.userRepository.deleteById(userId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public User Login(User user) {
        return this.userRepository.findByNameAndPassword(user.getName(), user.getPassword()).orElse(null);
    }
}
