package org.itsci.project.service;

import org.itsci.project.model.Course;
import org.itsci.project.model.Section;

import java.util.List;
import java.util.Map;

public interface CourseService {

    //CRUD 5 KINDS

    //GET ALL
    List<Course> get_ListCourse ();

    List<Course> get_ListCourseByIdUser(Long iduser);

    //GET BY ID
    Course get_CourseById(Long id);

    //CREATE
    Course add_Course(Map<String,String> map);

    //Update
    Course update_Course (Course course);

    //Delete
    void delet_Course(Long id);
}
