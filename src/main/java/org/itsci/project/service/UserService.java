package org.itsci.project.service;

import org.itsci.project.model.Login;
import org.itsci.project.model.User;
import org.itsci.project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface UserService {

    //List User teacher All
    List<User> get_ListTeacher (String typeuser);

    //GET  BY ID (User teacher)
    User get_Teacher(String id);

    //CREATE (User teacher)
    User add_Teacher(Map<String,String> map) throws ParseException;

    //Update (User teacher)
    User update_Teacher (User user);

    //Delete (User teacher)
    void delet_Teacher(String id);

    //GET BY CONTAINING NAME (User teacher)
    List<User> getTeacherByfnameContainingIgnoreCase (String fname);

    //List User Student All
    List<User> get_ListStudent (String typeuser);

    //import User Student
    void insert_DataStudent(MultipartFile file) throws IOException, ParseException;

}
