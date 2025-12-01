package com.seol.booksrv.service;

import com.seol.booksrv.dto.PropertyDto;
import com.seol.booksrv.mapper.PropertyMapper;
import com.seol.booksrv.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    public Optional<PropertyDto> getPropertyById(Integer id) {
        log.info("Fetching property by id: {}", id);
        return propertyRepository.findById(id)
                .map(propertyMapper::toDto);
    }

}
