package com.damirkin.springjpa.repository;

import com.damirkin.springjpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    Student findByFirstNameAndLastName(String firstName, String lastName);

    //JPQL
    @Query("select s from Student s where s.emailId = ?1")
    Student blablablagetSudentByEmailAddress(String EmailId);

    //JPQL
    @Query("select s.firstName from Student s where s.emailId = ?1")
    String blablablabla(String EmailId);

    //Native
    @Query(
            value = "SELECT * FROM my_students S where s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);

    //Native named parametr
    @Query(
            value = "SELECT * FROM my_students S where s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParametr(
            @Param("emailId") String emailId);

    @Modifying
    @Transactional
    @Query(
            value = "update my_students set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    int updateFirstNameByEmail(String firstName, String emailId);

}
