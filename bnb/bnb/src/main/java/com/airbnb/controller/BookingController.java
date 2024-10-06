package com.airbnb.controller;


import com.airbnb.entity.AppUser;
import com.airbnb.entity.Booking;
import com.airbnb.entity.Property;
import com.airbnb.entity.Rooms;
import com.airbnb.repository.BookingRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.RoomsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/booking")
public class BookingController {

    private RoomsRepository roomsRepository;
    private PropertyRepository propertyRepository;
    private BookingRepository bookingRepository;

    public BookingController(RoomsRepository roomsRepository, PropertyRepository propertyRepository, BookingRepository bookingRepository) {
        this.roomsRepository = roomsRepository;
        this.propertyRepository = propertyRepository;
        this.bookingRepository = bookingRepository;
    }

    @PostMapping("/createBooking")
    public ResponseEntity<?>createBooking(@RequestParam long propertyId,
                                          @RequestParam String roomType,
                                          @RequestBody Booking booking,
                                          @AuthenticationPrincipal AppUser appUser
                                          ){
        Property property = propertyRepository.findById(propertyId).get();
        List<LocalDate> datesBetween = getDatesBetween(booking.getCheckInDate(), booking.getCheckOutDate());
        List<Rooms> rooms = new ArrayList<>();
        for(LocalDate date:datesBetween){

            Rooms room = roomsRepository.findRoomsByPropertyIdAndTypeAndDate(propertyId,roomType,date);
            if(room.getCount()==0){
                return new ResponseEntity<>("room not available", HttpStatus.INTERNAL_SERVER_ERROR);
            }
                    rooms.add(room);

            }
                       //Booking
                           float count =0;
                    for(Rooms room:rooms) {
                        count = count +room.getCount();
                    }
                           booking.setTotal_price(count);
                           booking.setProperty(property);
                           booking.setAppUser(appUser);
                           booking.setTypeOfRoom(roomType);
        Booking saveBooking = bookingRepository.save(booking);

        if(saveBooking!=null) {
            for (Rooms room : rooms) {
                Long availableRooms = room.getCount();
                room.setCount(availableRooms - 1);
                roomsRepository.save(room);
            }

        }
     return new ResponseEntity<>(saveBooking,HttpStatus.CREATED);
    }
    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();

        // Iterate through the dates
        while (!startDate.isAfter(endDate)) {
            dates.add(startDate);
            startDate = startDate.plusDays(1);  // Move to the next date
        }

        return dates;
    }

    public static void main(String[] args) {
        LocalDate checkInDate = LocalDate.of(2023, 9, 1);
        LocalDate checkOutDate = LocalDate.of(2023, 9, 5);

        List<LocalDate> datesBetween = getDatesBetween(checkInDate, checkOutDate);

        // Print the dates
        datesBetween.forEach(date -> {
            System.out.println(date);
        });

    }


}
