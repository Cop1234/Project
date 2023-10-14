package org.itsci.project.controller;

import org.itsci.project.model.Login;
import org.itsci.project.model.User;
import org.itsci.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudenController {

    @Autowired
    private UserService userService;

    //listStudent
    @RequestMapping("/list")
    public ResponseEntity get_ListStudent (){
        try {
            String typeuser = "Student";
            List<User> users = userService.get_ListStudent(typeuser);
            return new ResponseEntity<>(users , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list student!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            System.out.println("FILE NAME IS : " + file.getOriginalFilename());
            userService.insert_DataStudent(file);

            return new ResponseEntity<>("List student success", HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>("Failed to list student!", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    //ubdate Student
    @PutMapping("/update")
    public ResponseEntity update_Student(@RequestBody Map<String,String> map) {
        try {
            User user = userService.update_Student(map);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update Teacher!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //getid
    @RequestMapping("/getbyid/{id}")
    public ResponseEntity get_Student (@PathVariable("id") String id){
        try {
            User users = userService.get_Student(id);
            return new ResponseEntity<>(users , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Teacher!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delet_Student (@PathVariable("id") String id){
        try {
            //เรียก SubjectById
            User users = userService.get_Student(id);
            String us = users.getUserid();
            //ลบ
            userService.delet_Student(id);

            return new ResponseEntity<>("User " + us + " was deleted!", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete User by id!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/updatepassword")
    public ResponseEntity PasswordEncoderStudent(@RequestBody Map<String,String> map) {
        try {
            Login login = userService.do_updateStudentProfile(map);
            return new ResponseEntity<>(login, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update Teacher!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
