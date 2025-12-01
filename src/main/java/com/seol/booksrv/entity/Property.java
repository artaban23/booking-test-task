package com.seol.booksrv.entity;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Property {

    private Integer propertyId;
    private String name;
    private List<Block> blocks;

}
