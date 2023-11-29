package com.example.springsecuritydemo27nov.services;

import com.example.springsecuritydemo27nov.configs.CustomPasswordEncoder;
import com.example.springsecuritydemo27nov.models.Role;
import com.example.springsecuritydemo27nov.models.User;
import com.example.springsecuritydemo27nov.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy CustomPasswordEncoder customPasswordEncoder) {
        this.userRepository = userRepository;
        this.customPasswordEncoder = customPasswordEncoder;
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> index() {
        return userRepository.findAll();
    }
    public User show(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public void save(User user) {

        User userToBeSaved = new User();

        userToBeSaved.setId(user.getId());
        userToBeSaved.setFirstName(user.getFirstName());
        userToBeSaved.setLastName(user.getLastName());
        userToBeSaved.setAge(user.getAge());
        userToBeSaved.setEmail(user.getEmail());
        userToBeSaved.setUsername(user.getUsername());
        userToBeSaved.setPassword(customPasswordEncoder.encode(user.getPassword()));
        userToBeSaved.setRoles(user.getRoles());

        userRepository.save(userToBeSaved);
    }
    public void update(Long id, User user) {
        save(user);
    }
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));

        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
