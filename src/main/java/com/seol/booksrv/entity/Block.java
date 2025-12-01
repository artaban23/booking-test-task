package com.seol.booksrv.entity;

import com.seol.booksrv.entity.types.BlockType;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Block {

    private UUID blockId;

    private String reason;

    private Property property;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean cancelled;

    private String guestDetails;

    private BlockType type;
}
