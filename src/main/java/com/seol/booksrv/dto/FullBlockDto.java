package com.seol.booksrv.dto;

import com.seol.booksrv.entity.types.BlockType;
import lombok.Builder;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@With
public record FullBlockDto (

    UUID blockId,
     String reason,
     Integer propertyId,
     LocalDate startDate,
     LocalDate endDate,
     boolean cancelled,
     String guestDetails,
     BlockType type
){}
