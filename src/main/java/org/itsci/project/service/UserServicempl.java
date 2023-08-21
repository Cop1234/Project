package org.itsci.project.service;



import org.itsci.project.model.Authority;
import org.itsci.project.model.Login;
import org.itsci.project.model.User;
import org.itsci.project.repository.AuthorityRepository;
import org.itsci.project.repository.LoginRepository;
import org.itsci.project.repository.StudentRepository;
import org.itsci.project.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServicempl implements UserService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    //List User teacher All
    @Override
    public List<User> get_ListTeacher(String typeuser) {
        return teacherRepository.getTeacherBytypeuserContainingIgnoreCase(typeuser);
    }

    //Get User teacher By id
    @Override
    public User get_Teacher(String id) {
        return teacherRepository.getReferenceById(id);
    }

    //Add User teacher
    @Override
    public User add_Teacher(Map<String, String> map) throws ParseException {
         String userid = map.get("userid");
         String typeuser = "Teacher";
         String email = map.get("email");
         String fname = map.get("fname");
         String lname = map.get("lname");
         DateFormat Day = new SimpleDateFormat("dd/MM/yyyy");
         String birthdate = map.get("birthdate");
         Date birthdates = Day.parse(birthdate);
         String gender = map.get("gender");

         String[] ps = birthdate.split("/");
         String ps_day = ps[0];
         String ps_month = ps[1];
         String ps_year = ps[2];
        //Create new object
        Login login = new Login();
        login.setUsername("MJU"+userid);
        login.setPassword("MJU@"+ps_day+ps_month+ps_year);
        loginRepository.save(login);

        //AddRoleForLogin
        Long TeacherRoleId = Long.parseLong("2");
        Set<Authority> roleSet = null;
        Authority authority = authorityRepository.findById(TeacherRoleId).get();
        login = loginRepository.findById(login.getId()).get();
        roleSet = login.getRole();
        roleSet.add(authority);
        login.setRole(roleSet);
        loginRepository.save(login);

        User user = new User();
        User users =  new User(user.getId(),userid,typeuser,email,fname,lname,birthdates,gender,login);

        //Save Object to DB
        return teacherRepository.save(users);
    }

    //Update User teacher
    @Override
    public User update_Teacher(User user) {
        return teacherRepository.save(user);
    }

    //Delete User Teacher
    @Override
    public void delet_Teacher(String id) {
        User Id = teacherRepository.getReferenceById(id);
        teacherRepository.delete(Id);
        teacherRepository.findAll();
    }

    @Override
    public List<User> getTeacherByfnameContainingIgnoreCase(String fname) {
        return teacherRepository.getTeacherByfnameContainingIgnoreCase(fname);
    }


    //Student
    //List User Student All
    @Override
    public List<User> get_ListStudent(String stuTypeuser) {
        return studentRepository.getStudentBytypeuser(stuTypeuser);
    }


}
