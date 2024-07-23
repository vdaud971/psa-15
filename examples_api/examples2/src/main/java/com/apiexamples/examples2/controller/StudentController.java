package com.apiexamples.examples2.controller;

import com.apiexamples.examples2.entity.Student;
import com.apiexamples.examples2.paylod.NewStuDto;
import com.apiexamples.examples2.paylod.StudentDto;
import com.apiexamples.examples2.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/postStudentDetail")
public class StudentController {
   private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //http://localhost:8080/api/v1/postStudentDetail
    @PostMapping
    public ResponseEntity<?> createStudent(
            @Valid @RequestBody StudentDto StudentDto,
                     BindingResult result) {
                if(result.hasErrors()){
                  return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.OK);
        }

        StudentDto student1 = studentService.createStudent(StudentDto);
        return new ResponseEntity(student1, HttpStatus.CREATED);
    }
    @PostMapping("/id")
    public ResponseEntity<StudentDto>findById(@RequestParam long id){
        studentService.findById(id);
        return null;
    }


    //http://localhost:8080/api/v1/postStudentDetail?id=
    @DeleteMapping
    public ResponseEntity<String> deleteById(@RequestParam long id){
        studentService.deleteById(id);
        return new ResponseEntity<>("record is deleted",HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/postStudentDetail?id=
    @PutMapping
    public ResponseEntity<StudentDto> updateStudentRecord(@RequestParam long id,@RequestBody StudentDto StudentDto ){
        StudentDto studentDto = studentService.updateStudentRecord(id, StudentDto);
                return new ResponseEntity<>(studentDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<NewStuDto> listOfStudents(
            @RequestParam(name="pageNo",defaultValue = "0",required=false)int pageNo,
            @RequestParam(name="pageSize",defaultValue="3",required=false)int pageSize,
            @RequestParam(name="sort",defaultValue="name",required=false)String sortBy,
            @RequestParam(name="sortDir",defaultValue="asc",required=false)String sortDir
    ){
        NewStuDto newStuDto = studentService.listOfStudents(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(newStuDto,HttpStatus.OK);
    }

}
