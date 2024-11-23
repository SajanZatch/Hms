package com.hms.controller;

import com.hms.Service.PdfServices;
import com.hms.Service.TwilioService;
import com.hms.entity.Bookings;
import com.hms.entity.Property;
import com.hms.entity.Rooms;
import com.hms.repository.BookingsRepository;
import com.hms.repository.PropertyRepository;
import com.hms.repository.RoomsRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private PdfServices pdfServices;
    private PropertyRepository propertyRepository;
    private BookingsRepository bookingsRepository;
    private RoomsRepository roomsRepository;
    private TwilioService twilioService;

    public BookingController(PdfServices pdfServices, PropertyRepository propertyRepository, BookingsRepository bookingsRepository , RoomsRepository roomsRepository, TwilioService twilioService) {
        this.pdfServices = pdfServices;
        this.propertyRepository = propertyRepository;
        this.bookingsRepository = bookingsRepository;
        this.roomsRepository = roomsRepository;
        this.twilioService = twilioService;
    }




    @PostMapping("/create-bookings")
    public ResponseEntity<?>createBookings(
            @RequestParam long propertyId,
            @RequestParam String roomType,
            @RequestBody Bookings booking
    ){
        Property property = propertyRepository.findById(propertyId).get();

        List<Rooms> rooms = roomsRepository.findByRoomTypeAndProperty(booking.getFromDate(), booking.getToDate(),roomType,property);

        for(Rooms room:rooms) {
            if (room.getCount() == 0) {
                return new ResponseEntity<>("No Rooms for "+room.getDate()+" Available", HttpStatus.OK);
            }
        }
        //Rooms rooms = roomsRepository.findByRoomTypeAndProperty(roomType,property);

        Bookings savedBooking = bookingsRepository.save(booking);
        if(savedBooking!=null){
        for(Rooms room:rooms){
            room.setCount(room.getCount()-1);
            roomsRepository.save(room);
        }
        }
        pdfServices.generateBookingPdf("C:\\Users\\sajan\\OneDrive\\Desktop\\Projects\\hms_bookings\\Conformation_Order-"+savedBooking.getId()+".pdf",property);
        twilioService.sendSms("+918249129059","Booking Successful. Your booing id is:"+booking.getId());
        twilioService.sendMessage("+8249129059","Booking Successful. Your booing id is:"+booking.getId());
        return new ResponseEntity<>(rooms,HttpStatus.OK);
    }

}
