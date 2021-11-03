package com.damirkin.springjpa.repository;

import com.damirkin.springjpa.entity.Course;
import com.damirkin.springjpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository repository;

    @Test
    public void saveTeacher() {
        Course courseFRO = Course.builder()
                .title("FROOOOO")
                .credit(5)
                .build();

        Course coursePRO = Course.builder()
                .title("PROOOOO")
                .credit(8)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("DamirSan")
                .firstName("Crey")
                //.courses(List.of(courseFRO, coursePRO))
                .build();

        repository.save(teacher);

    }

}