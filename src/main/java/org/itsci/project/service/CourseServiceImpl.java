package org.itsci.project.service;

import org.itsci.project.model.Course;
import org.itsci.project.model.Subject;
import org.itsci.project.model.User;
import org.itsci.project.repository.CourseRepository;
import org.itsci.project.repository.SubjectRepository;
import org.itsci.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Course> get_ListCourse() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> get_ListCourseByIdUser(Long iduser) {
        List<Course> courses = courseRepository.findByUserId(iduser);
        return courses;
    }

    @Override
    public Course get_CourseById(Long id) {
        return courseRepository.getReferenceById(id);
    }

    @Override
    public Course add_Course(Map<String, String> map) {
        String subjectId = map.get("subjectId");
        String userId = map.get("userId");
        String term = map.get("term");
        String semester = map.get("semester").trim();
        int semesterInt = Integer.parseInt(semester);
        int termInt = Integer.parseInt(term);
        long userIdLong = Long.parseLong(userId);

        // ดึง Subject และ User จากฐานข้อมูล (ในตัวอย่างนี้เปรียบเทียบกับการใช้ค่าของ subjectIdStr และ userIdStr)
        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        User user = userRepository.findById(userIdLong).orElse(null);

        if (subject == null || user == null) {
            // จัดการกรณีที่ไม่พบ Subject หรือ User
            // คุณสามารถจัดการตามความเหมาะสม เช่น โยนข้อผิดพลาดหรือทำอื่น ๆ ตามที่คุณต้องการ
            throw new IllegalArgumentException("Subject or User not found");
        }

        Course course = new Course();
        course.setSubject(subject);
        course.setUser(user);
        course.setTerm(termInt);
        course.setSemester(semesterInt);

        return courseRepository.save(course);
    }

    @Override
    public Course update_Course(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void delet_Course(Long id) {
        Course Id = courseRepository.getReferenceById(id);
        courseRepository.delete(Id);
        courseRepository.findAll();
    }
}
