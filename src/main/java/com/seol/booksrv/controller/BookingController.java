package com.seol.booksrv.controller;

import com.seol.booksrv.dto.BookingDto;
import com.seol.booksrv.dto.CreateBookingRequest;
import com.seol.booksrv.mapper.BookingMapper;
import com.seol.booksrv.service.BlockService;
import com.seol.booksrv.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@Slf4j
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody CreateBookingRequest request) {
        BookingDto bookingDto = bookingService.createBooking(request);
        log.info("Created booking with id: {}", bookingDto.bookingId());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable UUID id, @RequestBody BookingDto request) {
        BookingDto bookingDto = bookingService.updateBooking(request);
        log.info("Updated block with id: {}", id);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable UUID id) {
        BookingDto bookingById = bookingService.getBookingById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookingById);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteBooking(@PathVariable UUID id) {
        boolean b = bookingService.deleteBooking(id);
        HttpStatus stat = b ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(stat).body(id);
    }
}
