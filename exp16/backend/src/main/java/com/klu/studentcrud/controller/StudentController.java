package com.klu.studentcrud.controller;

import com.klu.studentcrud.dto.ApiErrorResponse;
import com.klu.studentcrud.entity.Student;
import com.klu.studentcrud.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@Tag(name = "Student CRUD APIs", description = "Endpoints for adding, viewing, updating, and deleting student records")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(
        summary = "Add a new student",
        description = "Creates a new student record in the database"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Student created successfully",
            content = @Content(schema = @Schema(implementation = Student.class))),
        @ApiResponse(responseCode = "400", description = "Validation or duplicate email error",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> addStudent(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Student payload",
            required = true,
            content = @Content(
                schema = @Schema(implementation = Student.class),
                examples = @ExampleObject(
                    value = "{\"name\":\"Bhavana Reddy\",\"email\":\"bhavana.reddy@example.com\",\"course\":\"FSAD\"}"
                )
            )
        )
        @Valid @RequestBody Student student
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(student));
    }

    @Operation(summary = "Retrieve all students", description = "Fetches all available student records")
    @ApiResponse(responseCode = "200", description = "Students retrieved successfully",
        content = @Content(schema = @Schema(implementation = Student.class)))
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @Operation(summary = "Retrieve a student by ID", description = "Fetches one student using the given ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student retrieved successfully",
            content = @Content(schema = @Schema(implementation = Student.class))),
        @ApiResponse(responseCode = "404", description = "Student not found",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @Operation(summary = "Update a student", description = "Updates the details of an existing student")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student updated successfully",
            content = @Content(schema = @Schema(implementation = Student.class))),
        @ApiResponse(responseCode = "400", description = "Validation or duplicate email error",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Student not found",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> updateStudent(
        @PathVariable Long id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Updated student payload",
            required = true,
            content = @Content(
                schema = @Schema(implementation = Student.class),
                examples = @ExampleObject(
                    value = "{\"name\":\"Bhavana Reddy\",\"email\":\"bhavana.updated@example.com\",\"course\":\"Advanced FSAD\"}"
                )
            )
        )
        @Valid @RequestBody Student student
    ) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @Operation(summary = "Delete a student", description = "Deletes a student record using the given ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Student not found",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}
