package com.apiexamples.examples2.paylod;

import lombok.Data;

import java.util.List;

@Data
public class NewStuDto {

    private List<StudentDto> dto;

    private int pageNo;
    private int pageSize;
}
