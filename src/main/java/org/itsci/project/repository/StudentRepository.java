package org.itsci.project.repository;

import org.itsci.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface StudentRepository extends JpaRepository<User,String> {
    List<User> getStudentBytypeuser (String typeuser);
}
