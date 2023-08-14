package org.itsci.project.controller;


import org.itsci.project.model.Subject;
import org.itsci.project.model.User;
import org.itsci.project.repository.TeacherRepository;
import org.itsci.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private UserService teacherService;


    //listTeacher
    @RequestMapping("/list")
    public ResponseEntity get_ListTeacher (){
        try {
            String typeuser = "T";
            List<User> users = teacherService.get_ListTeacher(typeuser);
            return new ResponseEntity<>(users , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list teacher!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //getid
    @RequestMapping("/getbyid/{id}")
    public ResponseEntity get_Teacher (@PathVariable("id") String id){
        try {
            User users = teacherService.get_Teacher(id);
            return new ResponseEntity<>(users , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Teacher!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity add_Teacher(@RequestBody Map<String,String> map){
        try {
            User user = teacherService.add_Teacher(map);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Add Teacher!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //ubdate subject
    @PutMapping("/update")
    public ResponseEntity update_Teacher(@RequestBody User users) {
        try {
            User user = teacherService.update_Teacher(users);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update Teacher!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    delete subject
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delet_Teacher (@PathVariable("id") String id){
        try {
            //เรียก SubjectById
            User users = teacherService.get_Teacher(id);
            String us = users.getUserid();
            //ลบ
            teacherService.delet_Teacher(id);

            return new ResponseEntity<>("User " + us + " was deleted!", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete User by id!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //หาข้อมูลทั้งหมดด้วยตัวอักษร
    @GetMapping("/getbycontname/{fname}")
    public ResponseEntity getSubjectsBySubjectNameContainingIgnoreCase (@PathVariable("fname") String fname){
        try {
            List<User> users = teacherService.getTeacherByfnameContainingIgnoreCase(fname);
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to getUser by name", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
