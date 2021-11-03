package com.damirkin.springjpa.repository;

import com.damirkin.springjpa.entity.Course;
import com.damirkin.springjpa.entity.Student;
import com.damirkin.springjpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    
    @Autowired
    private CourseRepository repository;
    
    @Test
    public void printCourses() {
        List<Course> courses =
                repository.findAll();

        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Djon")
                .lastname("Block")
                .build();

        Course course = Course
                .builder()
                .title("Python")
                .credit(9)
                .teacher(teacher)
                .build();

        repository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPagewithThreeRecords =
                PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords =
                PageRequest.of(1, 2);
        
        long totalElements = repository.findAll(firstPagewithThreeRecords)
                .getTotalElements();

        
        long tatalPages = repository.findAll(firstPagewithThreeRecords)
                .getTotalPages();

        List<Course> courses =
                repository.findAll(firstPagewithThreeRecords).getContent();

        System.out.println("totalElements = " + totalElements);

        System.out.println("tatalPages = " + tatalPages);

        System.out.println("courses = " + courses);
    }

    @Test
    public void findAllSortin() {
        Pageable sortByTitle =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title")
                );

        Pageable sortByCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("credit").descending()
                );

        Pageable sortByTitleAndCreditDesc = PageRequest.of(
                0,
                2,
                Sort.by("title")
                .descending()
                .and(Sort.by("credit"))
        );
        
        List<Course> courses 
                = repository.findAll(sortByTitle).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void printfindByTitleContaining() {
        Pageable firstPageTenRecords =
                PageRequest.of(0, 10);

        List<Course> courses =
                repository.findByTitleContaining(
                        "D",
                        firstPageTenRecords).getContent();
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {

        Teacher teacher = Teacher
                .builder()
                .firstName("Shakir")
                .lastname("Jorin")
                .build();

        Student student = Student
                .builder()
                .firstName("Geri")
                .lastName("Jeri")
                .emailId("Jeri@mail.ru")
                .build();

        Course course = Course
                .builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        repository.save(course);
    }



}