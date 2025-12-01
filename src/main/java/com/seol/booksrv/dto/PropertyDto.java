package com.seol.booksrv.dto;

import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
@With
public record PropertyDto(
        Integer propertyId,
        String name,
        List<FullBlockDto> blocks
) {}
