package org.itsci.project.service;

import org.itsci.project.model.Login;
import org.itsci.project.model.User;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> get_ListTeacher (String typeuser);

    //GET BY ID
    User get_Teacher(String id);

    //CREATE
    User add_Teacher(Map<String,String> map) throws ParseException;

    //Update
    User update_Teacher (User user);

    //Delete
//    void delet_Teacher(String id);

//    //GET BY CONTAINING NAME
//    List<Subject> getTeacherByfnameContainingIgnoreCase (String subjectName);
}
