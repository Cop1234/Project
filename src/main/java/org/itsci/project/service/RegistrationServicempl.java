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
    public List<Registration> get_ListRegistration() {
        return registrationRepository.findAll();
    }

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
            if (sectionOptional.isPresent() && userOptional.isPresent()) {
                Section section_id = sectionOptional.get();
                User userUserid = userOptional.get();
                // ตรวจสอบว่ารายการ Registration มีอยู่แล้วหรือไม่
                Optional<Registration> registrationOptional = registrationRepository.findByUserAndSection(userUserid, section_id);
                    if (userUserid.getFname().equals(fname) && userUserid.getLname().equals(lname) && userUserid.getUserid().equals(userid)) {
                        if (!registrationOptional.isPresent()) {
                            // ไม่มีรายการลงทะเบียน ดังนั้นสร้างรายการลงทะเบียนใหม่และบันทึกลงในฐานข้อมูล
                            Registration newRegistration = new Registration();
                            newRegistration.setSection(section_id);
                            newRegistration.setUser(userUserid);
                            registrationRepository.save(newRegistration);
                            System.out.println("New registration created for user: " + userUserid.getUserid() + " in section: " + section_id.getId());
                        } else {
                            System.out.println("Registration already exists for user: " + userUserid.getUserid() + " in section: " + section_id.getId());
                        }
                    } else {
                        System.out.println("Mismatch in user details for userid: " + userid);
                    }
            } else {
                System.out.println("Section or user not found for user: " + userid);
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
        return registrationRepository.findBySectionIdOrderByUserId(id);
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
    public Registration get_RegistrationIdBySectionIdandIdUser(Long sectionid, Long iduser) {
        return registrationRepository.getRegistrationBySection_IdAndUser_Id(sectionid,iduser);
    }


    @Override
    public void delet_Registration(Long id) {
        Registration Id = registrationRepository.getReferenceById(id);
        registrationRepository.delete(Id);
        registrationRepository.findAll();
    }




}
