package com.seol.booksrv.controller;

import com.seol.booksrv.dto.BookingDto;
import com.seol.booksrv.dto.CreateBookingRequest;
import com.seol.booksrv.mapper.BookingMapper;
import com.seol.booksrv.service.BlockService;
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

    private final BlockService blockService;
    private final BookingMapper bookingMapper;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody CreateBookingRequest request) {
        BookingDto bookingDto = blockService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@RequestBody BookingDto request) {
        BookingDto bookingDto = blockService.updateBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable UUID id) {
        BookingDto bookingById = blockService.getBookingById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookingById);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteBlock(@PathVariable UUID id) {
        boolean b = blockService.deleteBlock(id);
        HttpStatus stat = b ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(stat).body(id);
    }
}
