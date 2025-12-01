package com.seol.booksrv.dto;

import lombok.Builder;
import lombok.With;

import java.time.LocalDate;

@Builder
@With
public record CreateBlockRequest(
        String reason,
        Integer propertyId,
        LocalDate startDate,
        LocalDate endDate
) {}
