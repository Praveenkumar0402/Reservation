package com.service;

import com.dto.BookingDto;
import com.entity.Booking;
import com.entity.User;
import com.exceptions.NoBookingFound;
import com.exceptions.NoUsersFoundException;
import com.repository.BookingRepository;
import com.repository.UserRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    public List<BookingDto> findAll() {
        List<Booking> bookings = bookingRepository.findAllBookings();
        if (bookings.isEmpty()) {
            throw new NoUsersFoundException("sorry no bookings found");
        } else {
            List<BookingDto> bookingDtos = new ArrayList<>();
            for (Booking booking : bookings) {
                BookingDto bookingDto = new BookingDto(booking);
                bookingDtos.add(bookingDto);
            }
            return bookingDtos;
        }
    }

    public BookingDto updateBooking(int id, BookingDto bookingDto) {
        Booking booking = bookingRepository.findbookingbyid(id);
        if (booking == null) {
            throw new NoBookingFound("id is not present");
        } else {
            booking.setFrom(bookingDto.getFrom());
            booking.setTo(bookingDto.getTo());
            booking.setBookingdate(bookingDto.getBookingdate());
            booking.setBookingstatus(bookingDto.getBookingstatus());
            booking.setStateoftravel(bookingDto.getStateoftravel());
            bookingRepository.save(booking);
            return new BookingDto(booking);
        }
    }

    public BookingDto deleteBooking(int id) {
        Booking booking = bookingRepository.findbookingbyid(id);
        if (booking == null) {
            throw new NoUsersFoundException("id is not present");
        } else {
            bookingRepository.deleteById(id);
            return new BookingDto(booking);
        }
    }

    public BookingDto createBooking(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setBookingdate(bookingDto.getBookingdate());
        booking.setFrom(bookingDto.getFrom());
        booking.setTo(bookingDto.getTo());
        booking.setStateoftravel(bookingDto.getStateoftravel());
        booking.setBookingstatus(bookingDto.getBookingstatus());
        booking.setUserid(bookingDto.getUserid());
        bookingRepository.save(booking);
        return new BookingDto(booking);
    }

    public void generateExcel(HttpServletResponse response) throws IOException {
        List<Booking> bookings = bookingRepository.findAll();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("Booking details");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("from_adr");
        row.createCell(2).setCellValue("to_adr");
        row.createCell(3).setCellValue("booking_date");
        row.createCell(4).setCellValue("booking_status");
        row.createCell(5).setCellValue("state_of_travel");
        row.createCell(6).setCellValue("user_id");
        int rowindex = 1;
        for (Booking booking : bookings) {
            HSSFRow row1 = sheet.createRow(rowindex);
            row1.createCell(0).setCellValue(booking.getId());
            row1.createCell(1).setCellValue(booking.getFrom());
            row1.createCell(2).setCellValue(booking.getTo());
            row1.createCell(3).setCellValue(booking.getBookingdate());
            row1.createCell(4).setCellValue(booking.getBookingstatus());
            row1.createCell(5).setCellValue(booking.getStateoftravel().ordinal());
            row1.createCell(6).setCellValue(booking.getUserid());
            rowindex++;
        }
        ServletOutputStream servletOutputStream = response.getOutputStream();
        hssfWorkbook.write(servletOutputStream);
    }

    //Id based retrieved the data
    public List<BookingDto> findByemail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(()->new NoUsersFoundException("User not found"));
        List<Booking> bookings=bookingRepository.findByUserid(user.getId());
        List<BookingDto> bookingDtos=new ArrayList<>();
        if(bookings.isEmpty()){
            throw new NoBookingFound("No Bookings found");
        }
        else {
            for(Booking booking:bookings){
                BookingDto bookingDto=new BookingDto(booking);
                bookingDtos.add(bookingDto);
            }
        }
      return bookingDtos;
    }
}
