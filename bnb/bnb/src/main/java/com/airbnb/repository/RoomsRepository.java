package com.airbnb.repository;

import com.airbnb.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomsRepository extends JpaRepository<Rooms, Long> {

    // JPQL query to find rooms by room type and property ID
//    @Query("SELECT r FROM Rooms r WHERE r.type = :roomType AND r.property.id = :propertyId")
//    Rooms findByTypeAndPropertyId(@Param("roomType") String type, @Param("propertyId") Long propertyId);

    @Query("SELECT r FROM Rooms r WHERE r.property.id = :propertyId AND r.type = :roomType AND r.date = :date")
          Rooms findRoomsByPropertyIdAndTypeAndDate(
            @Param("propertyId") Long propertyId,
            @Param("roomType") String roomType,
            @Param("date") LocalDate date
    );
}