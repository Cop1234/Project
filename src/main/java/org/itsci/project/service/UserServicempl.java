package org.itsci.project.service;



import org.itsci.project.model.Login;
import org.itsci.project.model.User;
import org.itsci.project.repository.LoginRepository;
import org.itsci.project.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class UserServicempl implements UserService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public List<User> get_ListTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    public User get_Teacher(String fname) {
        return teacherRepository.getReferenceById(fname);
    }

    @Override
    public User add_Teacher(Map<String, String> map) throws ParseException {
         String userid = map.get("userid");
         String typeuser = map.get("typeuser");
         String email = map.get("email");
         String fname = map.get("fname");
         String lname = map.get("lname");
        DateFormat Day = new SimpleDateFormat("dd/mm/yyyy");
        String birthdate = map.get("birthdate");
         Date birthdates = Day.parse(birthdate);
         String gender = map.get("gender");

        //Create new object
        Login login = new Login();
        login.setUsername("MJU"+userid);
        login.setPassword("MJU@"+fname);


        loginRepository.save(login);

        User user = new User();
        User users =  new User(user.getId(),userid,typeuser,email,fname,lname,birthdates,gender,login);
//        user.setUserid(userid);
//        user.setTypeuser(typeuser);
//        user.setEmail(email);
//        user.setFname(fname);
//        user.setLname(lname);
//        user.setBirthdate(birthdates);
//        user.setGender(gender);

        //Save Object to DB
        return teacherRepository.save(users);
    }


}
