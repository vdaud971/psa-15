package com.airbnb.payload;


import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class PropertyDto {

    private Long id;

    private String name;

    private Long numberOfBeds;

    private Long numberOfBedroom;

    private Long numberOfBathroom;

    private long city;

    private long country;

}
