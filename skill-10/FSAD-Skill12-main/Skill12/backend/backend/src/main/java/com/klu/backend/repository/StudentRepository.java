package com.klu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.backend.model.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
