package com.seol.booksrv.service;

import com.seol.booksrv.dto.BookingDto;
import com.seol.booksrv.dto.CreateBookingRequest;
import com.seol.booksrv.entity.Block;
import com.seol.booksrv.exception.WrongWebUserArgumentException;
import com.seol.booksrv.mapper.BookingMapper;
import com.seol.booksrv.repository.PropertyRepository;
import com.seol.booksrv.utils.DateRangeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {
    private final PropertyRepository propertyRepository;
    private final BookingMapper bookingMapper;

    public BookingDto getBookingById(UUID bookingId) {
        log.info("Fetching booking by id: {}", bookingId);

        Optional<Block> blockById = propertyRepository.findBookingById(bookingId);
        BookingDto bookingDto = blockById.map(bookingMapper::toDto)
                .orElseThrow(() -> new WrongWebUserArgumentException("Missing booking: " + bookingId));

        return bookingDto;
    }

    public BookingDto createBooking(CreateBookingRequest request) {
        log.info("Creating Booking for property: {}", request.propertyId());
        Block block = bookingMapper.toEntity(request);
        DateRangeUtils.validateDateRangeBasic(block.getStartDate(), block.getEndDate());
        propertyRepository.addBlock(request.propertyId(), block);
        log.info("Booking created with id: {}", block.getBlockId());

        return bookingMapper.toDto(block);
    }

    public BookingDto updateBooking(BookingDto blockDto) {
        log.info("Updating booking with id: {}", blockDto.bookingId());
        DateRangeUtils.validateDateRangeBasic(blockDto.startDate(), blockDto.endDate());

        Block entity = bookingMapper.toEntity(blockDto);
        Block updated = propertyRepository.updateBlock(blockDto.propertyId(), entity);

        log.info("Updated booking with id: {}", blockDto.bookingId());
        return bookingMapper.toDto(updated);
    }

    public boolean deleteBooking(UUID id) {
        log.info("Deleting block with id: {}", id);
        boolean b = propertyRepository.deleteBlock(id);
        if (b) {
            log.info("Deleted block with id: {}", id);
        } else {
            log.info("block is already deleted: {}", id);
        }
        return b;
    }
}
