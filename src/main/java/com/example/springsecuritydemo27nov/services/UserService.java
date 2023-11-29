package com.example.springsecuritydemo27nov.services;


import com.example.springsecuritydemo27nov.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    List<User> index();
    User show(Long id);
    void save(User user);
    void update(Long id, User user);
    void delete(Long id);
}



