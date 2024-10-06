package com.airbnb;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class A {

    public static void main(String[] args) {
        String hashpw = BCrypt.hashpw("testing", BCrypt.gensalt(5));
        System.out.println(hashpw);
    }

    //            else{
//                Float nightlyPrice = room.getPrice();
//                float totalPrice = nightlyPrice * booking.getTotal_price();
//                booking.setTotal_price(totalPrice);
//                booking.setProperty(property);
//                booking.setAppUser(appUser);
//                booking.setTypeOfRoom(roomType);
//                Booking saveBooking = bookingRepository.save(booking);
//                if(saveBooking!=null) {
//                    Long val = room.getCount();
//                    room.setCount(val - 1);
//                    roomsRepository.save(room);
//                }
}
