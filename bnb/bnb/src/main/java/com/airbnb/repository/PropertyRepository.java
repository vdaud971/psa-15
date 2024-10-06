package com.airbnb.repository;

import com.airbnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {


//    @Query("Select p from Property p JOIN City c on p.city=c.id where c.name=:name")
//List<Property> searchProperty(
//       @Param("name") String name
//);

    @Query("Select p from Property p JOIN p.city c JOIN p.country co where c.name=:name OR co.name=:name")
    List<Property> searchProperty(
            @Param("name") String name
    );
}