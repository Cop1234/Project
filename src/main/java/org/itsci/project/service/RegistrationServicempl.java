package org.itsci.project.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.itsci.project.model.*;
import org.itsci.project.repository.RegistrationRepository;
import org.itsci.project.repository.SectionRepository;
import org.itsci.project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RegistrationServicempl implements RegistrationService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public void Import_Student(MultipartFile file, String id) throws IOException {

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
            String fname = row.getCell(1).getStringCellValue();
            String lname = row.getCell(2).getStringCellValue();


            Optional<Section> sectionOptional = sectionRepository.findById(Long.parseLong(id));
            Optional<User> userOptional = studentRepository.findByuserid(userid);
            Optional<Registration> registrationOptional = registrationRepository.findByUser_Id(userOptional.map(User::getId).orElse(-1L));

            if (sectionOptional.isPresent() && userOptional.isPresent() && registrationOptional.isPresent()) {
                Section section_id = sectionOptional.get();
                User userUserid = userOptional.get();
                Registration registration = registrationOptional.get();
                Long reg_idSec = registration.getSection().getId();

                if (userUserid.getFname().equals(fname) && userUserid.getLname().equals(lname) && userUserid.getUserid().equals(userid)) {
                    if (!reg_idSec.equals(section_id.getId())) {
                        Registration registrations = new Registration();
                        registrations.setSection(section_id);
                        registrations.setUser(userUserid);
                        registrationRepository.save(registrations);
                    }
                } else {
                    System.out.println("Mismatch in user details for userid: " + userid);
                }
            } else {
                User userUserid = userOptional.get();
                Section section_id = sectionOptional.get();
                Registration registrations = new Registration();
                registrations.setSection(section_id);
                registrations.setUser(userUserid);
                registrationRepository.save(registrations);
            }
            rowNum++;
        }
        workbook.close();
    }



    @Override
    public List<Registration> get_ViewSubject(String iduser) {
        Long id = Long.parseLong(iduser);
        return registrationRepository.findByUserId(id);
    }

    @Override
    public List<Registration> do_getViewStudent(String idsection) {
        Long id = Long.parseLong(idsection);
        return registrationRepository.findBySectionId(id);
    }

    @Override
    public Registration do_update(Map<String, String> map) {
        String userid = map.get("userid");

        String idsec = map.get("idsec");


        Optional<Section> sectionOptional = sectionRepository.findById(Long.parseLong(idsec));
        Optional<User> userOptional = studentRepository.findByuserid(userid);

            User userUserid = userOptional.get();
            Section section_id = sectionOptional.get();
            Registration registrations = new Registration();
            registrations.setSection(section_id);
            registrations.setUser(userUserid);


        return   registrationRepository.save(registrations);
    }

    @Override
    public Registration get_RegistrationById(Long id) {
        return registrationRepository.getReferenceById(id);
    }


    @Override
    public void delet_Registration(Long id) {
        Registration Id = registrationRepository.getReferenceById(id);
        registrationRepository.delete(Id);
        registrationRepository.findAll();
    }




}
