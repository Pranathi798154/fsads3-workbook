package com.klu.studentcrud.repository;

import java.util.Optional;

import com.klu.studentcrud.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}
