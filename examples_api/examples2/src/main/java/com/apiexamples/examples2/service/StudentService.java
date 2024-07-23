package com.apiexamples.examples2.service;


import com.apiexamples.examples2.paylod.NewStuDto;
import com.apiexamples.examples2.paylod.StudentDto;

import java.util.List;


public interface StudentService {

    public StudentDto createStudent(StudentDto StudentDto);

    public void deleteById(long id);

   public StudentDto updateStudentRecord(long id, StudentDto studentDto);

    NewStuDto listOfStudents(int pageNo, int pageSize, String sortBy, String sortDir);

    StudentDto findById(long id);
}
