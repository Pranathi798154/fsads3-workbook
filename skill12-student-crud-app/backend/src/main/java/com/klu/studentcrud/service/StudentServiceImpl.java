package com.klu.studentcrud.service;

import java.util.List;

import com.klu.studentcrud.entity.Student;
import com.klu.studentcrud.repository.StudentRepository;

import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        studentRepository.findByEmail(student.getEmail()).ifPresent(existingStudent -> {
            throw new RuntimeException("A student with this email already exists");
        });

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        studentRepository.findByEmail(student.getEmail()).ifPresent(foundStudent -> {
            if (!foundStudent.getId().equals(id)) {
                throw new RuntimeException("Another student already uses this email");
            }
        });

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setCourse(student.getCourse());

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student existingStudent = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        studentRepository.delete(existingStudent);
    }
}
