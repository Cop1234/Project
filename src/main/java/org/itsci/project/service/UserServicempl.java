package org.itsci.project.service;

import org.itsci.project.model.Authority;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.itsci.project.model.Login;
import org.itsci.project.model.User;
import org.itsci.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

    @Autowired
    private UserRepository userRepository;

    @Override
    public Long findUserIdByUsername(String username) {
        return userRepository.findUserIdByUsername(username);
    }

    @Override
    public User get_user(Long id) {
        return userRepository.getReferenceById(id);
    }

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
        String loginusername = map.get("email");
        String loginpassword = "MJU@123456";
         String userid = null;
         String typeuser = "Teacher";
         String email = map.get("email");
         String fname = map.get("fname");
         String lname = map.get("lname");
         DateFormat Day = new SimpleDateFormat("dd/MM/yyyy");
         String birthdate = map.get("birthdate");
         Date birthdates = Day.parse(birthdate);
         String gender = map.get("gender");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String hashedPassword = passwordEncoder.encode(loginpassword);


        //Create new object
        Login login = new Login();
        login.setUsername(loginusername);
        login.setPassword(hashedPassword);
        loginRepository.save(login);

        Authority authority = null;
        //AddRoleForLogin
        //Long TeacherRoleId = Long.parseLong("2");
        Set<Authority> roleSet = null;
        //login = loginRepository.findById(login.getId()).get();
        Optional<Login> loginOptional = loginRepository.findById(login.getId());
        if (loginOptional.isPresent()) {
            login = loginOptional.get();
            // ทำสิ่งที่คุณต้องการกับ login ที่พบ
        } else {
            // ไม่พบ login ที่ตรงเงื่อนไข
            new ResponseEntity<>("Failed to find login!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //authority = authorityRepository.findById(TeacherRoleId).get();
        Optional<Authority> authorityOptional = authorityRepository.findById(Long.parseLong("2"));
        if (authorityOptional.isPresent()) {
            authority = authorityOptional.get();
            // ทำสิ่งที่คุณต้องการกับ authority ที่พบ
        } else {
            // ไม่พบ authority ที่ตรงเงื่อนไข
            new ResponseEntity<>("Failed to find Authority!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        roleSet = login.getRole();
        roleSet.add(authority);
        login.setRole(roleSet);
        loginRepository.save(login);

        User user = new User();
        User users = new User(user.getId(),userid,typeuser,email,fname,lname,birthdates,gender,login);

        //Save Object to DB
        return teacherRepository.save(users);
    }

    //Update User teacher
    @Override
    public User update_Teacher(Map<String, String> map) throws ParseException {
        // รับข้อมูลที่ต้องการอัปเดตจาก map
        String Id = map.get("id");
        String email = map.get("email");
        String firstName = map.get("fname");
        String lastName = map.get("lname");
        String birthdate = map.get("birthdate");
        String gender = map.get("gender");

        // ค้นหา Teacher ที่ต้องการอัปเดต
        User user = teacherRepository.findById(Id).get();
        user.setEmail(email);
        user.setFname(firstName);
        user.setLname(lastName);
        // อัปเดตวันเกิด
        DateFormat dayFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = dayFormat.parse(birthdate);
        user.setBirthdate(birthDate);
        user.setGender(gender);
        // บันทึกข้อมูล Teacher ที่อัปเดตลงในฐานข้อมูล
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

    @Override
    public void insert_DataStudent(MultipartFile file) throws  IOException, ParseException {

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum = 0; // ใช้ตรวจสอบว่าเราอยู่ที่แถวที่ 0 หรือไม่
        for (Row row : sheet) {
            if (rowNum == 0) {
                // ข้ามการอ่านข้อมูลในแถวที่ 0
                rowNum++;
                continue;
            }


            String userid = row.getCell(0).getStringCellValue();
            String typeuser = "Student";

            String fname = row.getCell(1).getStringCellValue();
            String lname = row.getCell(2).getStringCellValue();
            DateFormat Day = new SimpleDateFormat("dd/MM/yyyy");
            String birthdate = row.getCell(3).getStringCellValue();
            Date birthdates = Day.parse(birthdate);
            String gender = row.getCell(4).getStringCellValue();

            String email = "MJU"+userid+"@mju.ac.th";


            String[] ps = birthdate.split("/");
            String ps_day = ps[0];
            String ps_month = ps[1];
            String ps_year = ps[2];


            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            String hashedPassword = passwordEncoder.encode("MJU@" + ps_day + ps_month + ps_year);

            //User userUserid = studentRepository.findByuserid(userid).get();
            Optional<User> userOptional = studentRepository.findByuserid(userid);
            User userUserid = userOptional.orElse(null);

            if(userUserid == null){
//Create new object
                Login login = new Login();
                login.setUsername("MJU"+userid);
                login.setPassword(hashedPassword);
                loginRepository.save(login);

                //AddRoleForLogin
                //Long StudentRoleId = Long.parseLong("1");
                Authority authority = null;
                Set<Authority> roleSet = null;
                //Authority authority = authorityRepository.findById(StudentRoleId).get();
                //login = loginRepository.findById(login.getId()).get();
                Optional<Login> loginOptional = loginRepository.findById(login.getId());
                if (loginOptional.isPresent()) {
                    login = loginOptional.get();
                    // ทำสิ่งที่คุณต้องการกับ login ที่พบ
                } else {
                    // ไม่พบ login ที่ตรงเงื่อนไข
                    new ResponseEntity<>("Failed to find login!", HttpStatus.INTERNAL_SERVER_ERROR);
                }
                //authority = authorityRepository.findById(TeacherRoleId).get();
                Optional<Authority> authorityOptional = authorityRepository.findById(Long.parseLong("1"));
                if (authorityOptional.isPresent()) {
                    authority = authorityOptional.get();
                    // ทำสิ่งที่คุณต้องการกับ authority ที่พบ
                } else {
                    // ไม่พบ authority ที่ตรงเงื่อนไข
                    new ResponseEntity<>("Failed to find Authority!", HttpStatus.INTERNAL_SERVER_ERROR);
                }

                roleSet = login.getRole();
                roleSet.add(authority);
                login.setRole(roleSet);
                loginRepository.save(login);
                User user = new User();
                user.setUserid(userid);
                user.setEmail(email);
                user.setFname(fname);
                user.setLname(lname);
                user.setBirthdate(birthdates);
                user.setGender(gender);
                user.setTypeuser(typeuser);
                user.setLogin(login);
                studentRepository.save(user);
            } else if (!userUserid.getUserid().equals(userid)){
                //Create new object
                Login login = new Login();
                login.setUsername("MJU"+userid);
                login.setPassword(hashedPassword);
                loginRepository.save(login);

                //AddRoleForLogin
                //Long StudentRoleId = Long.parseLong("1");
                Authority authority = null;
                Set<Authority> roleSet = null;
                //Authority authority = authorityRepository.findById(StudentRoleId).get();
                //login = loginRepository.findById(login.getId()).get();
                Optional<Login> loginOptional = loginRepository.findById(login.getId());
                if (loginOptional.isPresent()) {
                    login = loginOptional.get();
                    // ทำสิ่งที่คุณต้องการกับ login ที่พบ
                } else {
                    // ไม่พบ login ที่ตรงเงื่อนไข
                    new ResponseEntity<>("Failed to find login!", HttpStatus.INTERNAL_SERVER_ERROR);
                }
                //authority = authorityRepository.findById(TeacherRoleId).get();
                Optional<Authority> authorityOptional = authorityRepository.findById(Long.parseLong("1"));
                if (authorityOptional.isPresent()) {
                    authority = authorityOptional.get();
                    // ทำสิ่งที่คุณต้องการกับ authority ที่พบ
                } else {
                    // ไม่พบ authority ที่ตรงเงื่อนไข
                    new ResponseEntity<>("Failed to find Authority!", HttpStatus.INTERNAL_SERVER_ERROR);
                }

                roleSet = login.getRole();
                roleSet.add(authority);
                login.setRole(roleSet);
                loginRepository.save(login);
                User user = new User();
                user.setUserid(userid);
                user.setEmail(email);
                user.setFname(fname);
                user.setLname(lname);
                user.setBirthdate(birthdates);
                user.setGender(gender);
                user.setTypeuser(typeuser);
                user.setLogin(login);
                studentRepository.save(user);

            }else{
                Long id = userUserid.getId();
                User user = studentRepository.findById(id.toString()).get();
                user.setUserid(userid);
                user.setEmail(email);
                user.setFname(fname);
                user.setLname(lname);
                user.setBirthdate(birthdates);
                user.setGender(gender);
                user.setTypeuser(typeuser);
                studentRepository.save(user);
            }
            rowNum++;
        }
        workbook.close();
    }

    @Override
    public User get_Student(String id) {
        return studentRepository.getReferenceById(id);
    }

    @Override
    public void delet_Student(String id) {
        User Id = studentRepository.getReferenceById(id);
        studentRepository.delete(Id);
        studentRepository.findAll();
    }




    @Override
    public User update_Student(Map<String, String> map) throws ParseException {
        String Id = map.get("id");
        String email = map.get("email");
        String firstName = map.get("fname");
        String lastName = map.get("lname");
        String birthdate = map.get("birthdate");
        String gender = map.get("gender");
        // ค้นหา Teacher ที่ต้องการอัปเดต
        User user = studentRepository.findById(Id).get();
        user.setEmail(email);
        user.setFname(firstName);
        user.setLname(lastName);
        // อัปเดตวันเกิด
        DateFormat dayFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = dayFormat.parse(birthdate);
        user.setBirthdate(birthDate);
        user.setGender(gender);
        // บันทึกข้อมูล Teacher ที่อัปเดตลงในฐานข้อมูล
        return studentRepository.save(user);
    }

    @Override
    public Login do_updateStudentProfile(Map<String, String> map) {
        String loginid = map.get("loginid");
        String password = map.get("password");
        // สร้างเจนเนอร์แบบ bcrypt
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Login login = loginRepository.findById(Long.parseLong(loginid)).get();
        // เข้ารหัสรหัสผ่านใหม่
        String hashedPassword = passwordEncoder.encode(password);
        login.setPassword(hashedPassword);
        return loginRepository.save(login);
    }



    @Override
    public Login UpdateTeacherProfile(Map<String, String> map) {
        String loginid = map.get("loginid");
        String password = map.get("password");
        // สร้างเจนเนอร์แบบ bcrypt
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Login login = loginRepository.findById(Long.parseLong(loginid)).get();
        // เข้ารหัสรหัสผ่านใหม่
        String hashedPassword = passwordEncoder.encode(password);
        login.setPassword(hashedPassword);
        return loginRepository.save(login);
    }


}