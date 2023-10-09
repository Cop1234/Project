package org.itsci.project.service;

import org.itsci.project.model.Course;
import org.itsci.project.model.Section;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface CourseService {

    //CRUD 5 KINDS

    //GET ALL
    List<Course> get_ListCourse ();

    //GET ALL BY IDUSER
    List<Course> get_ListCourseByIdUser(Long iduser);

    //GET BY ID
    Course get_CourseById(Long id);

    //CREATE
    Course add_Course(Map<String,String> map) throws ParseException;

    //Update
    Course update_Course (Map<String,String> map) throws ParseException;

    //Delete
    void delet_Course(Long id);
}
