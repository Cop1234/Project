package org.itsci.project.controller;

import org.itsci.project.model.User;
import org.itsci.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

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

    @PostMapping("/add")
    public String uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            userService.insert_DataStudent(file);
            return "File imported successfully!";

        } catch (IOException e) {
            return "Failed to import file: " + e.getMessage();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }



}
