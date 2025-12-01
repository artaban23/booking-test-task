package com.seol.booksrv.dto;

import lombok.Builder;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@With
public record BlockDto(
        UUID blockId,
        String reason,
        Integer propertyId,
        LocalDate startDate,
        LocalDate endDate
) {}
