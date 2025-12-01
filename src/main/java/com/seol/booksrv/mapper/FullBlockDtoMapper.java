package com.seol.booksrv.mapper;

import com.seol.booksrv.dto.FullBlockDto;
import com.seol.booksrv.entity.Block;
import com.seol.booksrv.entity.types.BlockType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = {BlockType.class})
public interface FullBlockDtoMapper {

    @Mapping(source = "propertyId", target = "property.propertyId")
    Block toEntity(FullBlockDto blockDto);

    @Mapping(source = "property.propertyId", target = "propertyId")
    FullBlockDto toDto(Block block);
}
