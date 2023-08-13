package org.itsci.project.service;

import org.itsci.project.model.Login;
import org.itsci.project.model.User;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> get_ListTeacher ();

    //GET BY ID
    User get_Teacher(String id);

    //CREATE
    User add_Teacher(Map<String,String> map) throws ParseException;

//    //Update
//    Subject update_Teacher (Subject subject);
//
//    //Delete
//    void delet_Teacher(String id);
//
//    //GET BY CONTAINING NAME
//    List<Subject> getUserByfnameContainingIgnoreCase (String subjectName);
}
