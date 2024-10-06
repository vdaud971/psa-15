package com.airbnb.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number_of_beds", nullable = false)
    private Long numberOfBeds;

    @Column(name = "number_of_bedroom", nullable = false)
    private Long numberOfBedroom;

    @Column(name = "number_of_bathroom")
    private Long numberOfBathroom;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

}