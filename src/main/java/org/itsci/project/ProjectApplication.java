package org.itsci.project;

import org.itsci.project.model.Authority;
import org.itsci.project.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

    @Autowired
    AuthorityRepository authorityRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception
    {
        Long id = Long.parseLong("1");
        Authority a = new Authority(id, "Student");
        authorityRepository.save(a);
        id = Long.parseLong("2");
        Authority b = new Authority(id, "Teacher");
        authorityRepository.save(b);
        id = Long.parseLong("3");
        Authority c = new Authority(id, "Admin");
        authorityRepository.save(c);
    }

}
