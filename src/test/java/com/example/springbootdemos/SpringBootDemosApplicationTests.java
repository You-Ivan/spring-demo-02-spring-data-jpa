package com.example.springbootdemos;

import com.example.springbootdemos.model.Student;
import com.example.springbootdemos.repository.StudentRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SpringBootDemosApplicationTests {

    @Autowired
    StudentRepository studentRepo;
    Student student = Student.builder()
            .name("Jack")
            .birthday(LocalDate.of(1998, 7, 10))
            .sex("male")
            .build();


    @Test
    void testSaveStudent() {
        Student savedStudent = studentRepo.save(student);
        System.out.println(savedStudent);
        studentRepo.deleteById(student.getId());
    }

    @Test
    void testGetStudent() {
        Student savedStudent = studentRepo.save(student);
        studentRepo.findById(savedStudent.getId())
                .ifPresent(s -> assertEquals(student, s));
        studentRepo.deleteById(student.getId());
    }

    @Test
    void testUpdateStudent() {
        Student savedStudent = studentRepo.save(student);
        savedStudent.setName("Kelly");
        studentRepo.save(savedStudent);
        studentRepo.findById(savedStudent.getId())
                .ifPresent(s -> assertEquals("Kelly", s.getName()));
        studentRepo.deleteById(student.getId());
    }

    @Test
    void testDeleteStudent() {
        Student savedStudent = studentRepo.save(student);
        studentRepo.deleteById(savedStudent.getId());
        assertTrue( studentRepo.findById(savedStudent.getId()).isEmpty());
    }
}
