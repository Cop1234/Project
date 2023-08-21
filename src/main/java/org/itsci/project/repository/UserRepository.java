package org.itsci.project.repository;

import org.itsci.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    //Optional<User> getUserByUsernameEquals (String username);
}
