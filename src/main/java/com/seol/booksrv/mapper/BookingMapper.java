package com.seol.booksrv.mapper;

import com.seol.booksrv.dto.BlockDto;
import com.seol.booksrv.dto.BookingDto;
import com.seol.booksrv.dto.CreateBlockRequest;
import com.seol.booksrv.dto.CreateBookingRequest;
import com.seol.booksrv.entity.Block;
import com.seol.booksrv.entity.types.BlockType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = {BlockType.class})
public interface BookingMapper {

    @Mapping(source = "bookingId", target = "blockId")
    @Mapping(source = "propertyId", target = "property.propertyId")
    @Mapping(target = "type",  expression = "java(BlockType.BOOKING)")
    Block toEntity(BookingDto BookingDto);

    @Mapping(source = "propertyId", target = "property.propertyId")
    @Mapping(target = "cancelled", constant = "false")
    @Mapping(target = "type",  expression = "java(BlockType.BOOKING)")
    Block toEntity(CreateBookingRequest request);

    @Mapping(source = "property.propertyId", target = "propertyId")
    @Mapping(source = "blockId", target = "bookingId")
    BookingDto toDto(Block block);

}
