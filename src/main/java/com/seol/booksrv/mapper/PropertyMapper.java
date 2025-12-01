package com.seol.booksrv.mapper;

import com.seol.booksrv.dto.PropertyDto;
import com.seol.booksrv.entity.Property;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = FullBlockDtoMapper.class)
public interface PropertyMapper {

    PropertyDto toDto(Property property);

    Property toEntity(PropertyDto propertyDto);

}
