package org.itsci.project.controller;

import org.itsci.project.model.Course;
import org.itsci.project.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/list")
    public ResponseEntity get_ListCourse (){
        try {
            List<Course> courses = courseService.get_ListCourse();
            return new ResponseEntity<>(courses , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list Course!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/getbyid/{id}")
    public ResponseEntity get_CourseById (@PathVariable("id") String id){
        try {
            Course course = courseService.get_CourseById(id);
            return new ResponseEntity<>(course , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Course by id!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity add_Course (@RequestBody Map<String,String> map){
        try {
            Course course = courseService.add_Course(map);
            return new ResponseEntity<>(course, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Add Course!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update_Course (@RequestBody Course course){
        try {
            Course update_course = courseService.update_Course(course);
            return new ResponseEntity<>(update_course, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Add Course!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delet_Course (@PathVariable("id") String id){
        try {
            Course course = courseService.get_CourseById(id);
            Long courseId = course.getId();
            courseService.delet_Course(id);

            return new ResponseEntity<>("Course " + courseId + " was deleted!", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete Course by id!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
