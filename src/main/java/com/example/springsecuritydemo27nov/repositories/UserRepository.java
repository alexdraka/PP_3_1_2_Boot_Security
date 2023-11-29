package com.example.springsecuritydemo27nov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springsecuritydemo27nov.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}

