package com.seol.booksrv.mapper;

import com.seol.booksrv.dto.BlockDto;
import com.seol.booksrv.dto.CreateBlockRequest;
import com.seol.booksrv.entity.Block;
import com.seol.booksrv.entity.types.BlockType;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = {BlockType.class})
public interface BlockMapper {

    @Mapping(source = "propertyId", target = "property.propertyId")
    @Mapping(target = "cancelled", constant = "false")
    @Mapping(target = "type",  expression = "java(BlockType.BLOCK)")
    Block toEntity(BlockDto blockDto);

    @Mapping(source = "propertyId", target = "property.propertyId")
    @Mapping(target = "cancelled", constant = "false")
    @Mapping(target = "type",  expression = "java(BlockType.BLOCK)")
    Block toEntity(CreateBlockRequest request);

    @Mapping(source = "property.propertyId", target = "propertyId")
    BlockDto toDto(Block block);

    List<BlockDto> toDTOList(List<Block> blocks);

}
