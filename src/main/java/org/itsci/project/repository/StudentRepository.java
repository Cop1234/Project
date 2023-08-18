package org.itsci.project.repository;

import org.itsci.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<User,String> {
    List<User> getStudentBytypeuser (String typeuser);
}
