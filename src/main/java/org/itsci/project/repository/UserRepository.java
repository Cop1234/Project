package org.itsci.project.repository;

import org.itsci.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u.id FROM User u WHERE u.login.username = :username")
    Long findUserIdByUsername(@Param("username") String username);
}
