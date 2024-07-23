package com.apiexamples.examples2.service;

import com.apiexamples.examples2.entity.Student;
import com.apiexamples.examples2.exception.ResourceNotFoundException;
import com.apiexamples.examples2.paylod.NewStuDto;
import com.apiexamples.examples2.paylod.StudentDto;
import com.apiexamples.examples2.repository.StudentRepository;
import org.hibernate.query.SortDirection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

  private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDto createStudent(StudentDto StudentDto) {
        Student student = mapToEntity(StudentDto);
        Student entity = studentRepository.save(student);
        StudentDto studentDto = mapToDto(entity);
        studentDto.setMsg("record is saved");
        return studentDto;
    }

    @Override
    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudentRecord(long id, StudentDto studentDto) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        Student student = optionalStudent.get();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setClass_of_Student(studentDto.getClass_of_Student());
        Student saveEntity = studentRepository.save(student);
        StudentDto studentDto1 = mapToDto(student);
        return studentDto1;
    }

    @Override
    public NewStuDto listOfStudents(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(Sort.Direction.ASC,sortBy):Sort.by(Sort.Direction.DESC,sortBy);
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
        Page<Student> student=studentRepository.findAll(pageRequest);
        List<StudentDto> collect = student.stream().map(r ->mapToDto(r)).collect(Collectors.toList());
        NewStuDto StuDto=new NewStuDto();
        StuDto.setDto(collect);
        StuDto.setPageSize(pageRequest.getPageSize());
        StuDto.setPageNo(pageRequest.getPageNumber());
        return StuDto;
    }

    @Override
    public StudentDto findById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("entered id is not valid" + id));
        StudentDto studentDto = mapToDto(student);
        return studentDto;
    }

    public Student mapToEntity(StudentDto dto){
        Student entity=new Student();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setClass_of_Student(dto.getClass_of_Student());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        return entity;
    }

    public StudentDto mapToDto(Student entity){
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setClass_of_Student(entity.getClass_of_Student());
        dto.setEmail(entity.getEmail());
        return dto;
    }
}

