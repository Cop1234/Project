package org.itsci.project.repository;


import org.itsci.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<User,String> {
    List<User> getTeacherByfnameContainingIgnoreCase (String fname);
    List<User> getTeacherBytypeuserContainingIgnoreCase (String typeuser);

    Optional<User> findById(String id);
}
