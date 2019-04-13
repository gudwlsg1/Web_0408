package kr.hs.dgsw.web_0326.Controller;

import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getlstUser(){
        return this.userService.getlstUser();
    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable Long userId){
        return this.userService.getUser(userId);
    }

    @GetMapping("/login/{userId}/{password}")
    public User Login(@PathVariable String userId, @PathVariable String password){
        User user = new User(userId, password);
        return this.userService.Login(user);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
       return this.userService.addUser(user);
    }

    @PutMapping("/user/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user){
        return this.userService.updateUser(userId, user);
    }

    @DeleteMapping("/user/{userId}")
    public boolean DeleteUser(@PathVariable Long userId){
        return this.userService.deleteUser(userId);
    }

}
