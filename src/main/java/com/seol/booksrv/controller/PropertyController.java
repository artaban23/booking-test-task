package com.seol.booksrv.controller;

import com.seol.booksrv.dto.PropertyDto;
import com.seol.booksrv.service.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
@Slf4j
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDto> getPropertyById(@PathVariable Integer id) {
        Optional<PropertyDto> propertyById = propertyService.getPropertyById(id);
        return propertyById
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
