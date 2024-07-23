package com.apiexamples.examples2.paylod;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class StudentDto {


    private Long id;
 @Size(min=3,message = "letters_should be more than three")
    private String name;

    private String class_of_Student;
    @Email(message = "email addrees is not in right format")
    private String email;
    private String msg;

}