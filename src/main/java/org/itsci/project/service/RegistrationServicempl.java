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

@Service
public class RegistrationServicempl implements RegistrationService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public void Import_Student(MultipartFile file,String id) throws  IOException {

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Section section_id = sectionRepository.findById(Long.parseLong(id)).get();
        for (Row row : sheet) {
            String userid = row.getCell(0).getStringCellValue();
            String fname = row.getCell(1).getStringCellValue();
            String lname = row.getCell(2).getStringCellValue();

            String regisStatus = "ลงทะเบียนเรียน";

            User userUserid = studentRepository.findByuserid(userid).get();

            if (userUserid.getFname().equals(fname)){
                    if (userUserid.getLname().equals(lname)){
                        if (userUserid.getUserid().equals(userid)){
                            Registration registration = new Registration();
                            registration.setSection(section_id);
                            registration.setUser(userUserid);
                            registration.setRegisStatus(regisStatus);
                            registrationRepository.save(registration);
                        }else {
                            System.out.println("null value userid! "+userid);
                        }
                    }else {
                        System.out.println("null value lname! "+lname);
                    }
                }else {
                    System.out.println("null value fname!" + fname);
                }
        }
        workbook.close();
    }



    @Override
    public List<Registration> get_ViewSubject(String iduser) {
        Long id = Long.parseLong(iduser);
        return registrationRepository.findByUserId(id);
    }


}
