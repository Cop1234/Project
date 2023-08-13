package org.itsci.project.service;

import org.itsci.project.model.User;

import java.util.List;

public interface TeacherService {

    List<User> get_ListTeacher ();

//    //GET BY ID
//    User get_Teacher(String id);

//    //CREATE
//    Subject add_Teacher(Map<String,String> map);
//
//    //Update
//    Subject update_Teacher (Subject subject);
//
//    //Delete
//    void delet_Teacher(String id);
//
//    //GET BY CONTAINING NAME
//    List<Subject> getUserByfnameContainingIgnoreCase (String subjectName);
}
