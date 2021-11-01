package com.damirkin.springjpa.repository;

import com.damirkin.springjpa.entity.Guardian;
import com.damirkin.springjpa.entity.Student;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("askol@rumail.ru")
                .firstName("Vandam")
                .lastName("Junior")
                //.guardianName("Vandam")
                //.guardianEmail("askoldVan@rumail.ru")
                //.guardianMobile("9999999999")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> studentList =
                studentRepository.findAll();

        System.out.println("studentList = " + studentList);
    }

    @Test
    public void saveStudentWithGuardian() {

        Guardian guardian = Guardian.builder()
                .name("Doris")
                .email("doris@rumail.ru")
                .mobile("2323232323")
                .build();

        Student student = Student.builder()
                .emailId("barak@rumail.ru")
                .firstName("Abama")
                .lastName("Barak")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printStudentByFirstName() {
        List<Student> students =
                studentRepository.findByFirstName("Vandam");

        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> students = studentRepository.findByFirstNameContaining("Va");

        System.out.println("students = " + students);

    }

    @Test
    public void printStudentBuGuardian() {
        List<Student> students = studentRepository.findByGuardianName("Doris");

        System.out.println("students = " + students);
    }

    @Test
    public void getStudentByEmail() {
        Student student = studentRepository.blablablagetSudentByEmailAddress(
                "barak@rumail.ru"
        );

        System.out.println("student = " + student);
    }

    @Test
    public void getStudentFirstNameByEmail() {
        String firstName = studentRepository.blablablabla(
                "barak@rumail.ru"
        );

        System.out.println("firstName = " + firstName);
    }
    
    @Test
    public void getStudentWithNativeQueryByEmail() {
        Student student = studentRepository.getStudentByEmailAddressNative(
                "barak@rumail.ru"
        );

        System.out.println("student = " + student);
    }

    @Test
    public void getStudentWithNativeQueryByEmailNamedParam() {
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParametr(
                "barak@rumail.ru"
        );

        System.out.println("student = " + student);
    }

    @Test
    public void uddateStudentNameByEmailNativeQuery() {
        int inti = studentRepository.updateFirstNameByEmail(
                "Van Dam",
                "askol@rumail.ru"
        );

        System.out.println("inti = " + inti);

    }



}