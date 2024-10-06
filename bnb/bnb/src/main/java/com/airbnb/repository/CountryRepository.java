package com.airbnb.repository;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findById(Long id);
}