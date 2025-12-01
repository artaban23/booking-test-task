package com.seol.booksrv.controller;

import com.seol.booksrv.dto.BlockDto;
import com.seol.booksrv.dto.CreateBlockRequest;
import com.seol.booksrv.mapper.BlockMapper;
import com.seol.booksrv.service.BlockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/blocks")
@RequiredArgsConstructor
@Slf4j
public class BlockController {

    private final BlockService blockService;
    private final BlockMapper blockMapper;

    @GetMapping("/{id}")
    public ResponseEntity<BlockDto> getBlockById(@PathVariable UUID id) {
        BlockDto block = blockService.getBlockById(id);
        return ResponseEntity.ok(block);
    }

    @PostMapping
    public ResponseEntity<BlockDto> createBlock(@RequestBody CreateBlockRequest request) {
        try {
            BlockDto blockDto = blockService.createBlock(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(blockDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlockDto> updateBlock(@PathVariable UUID id, @RequestBody BlockDto blockDTO) {
        BlockDto blockDto = blockService.updateBlock(blockDTO);
        log.info("Updated block with id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(blockDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteBlock(@PathVariable UUID id) {
        boolean b = blockService.deleteBlock(id);
        HttpStatus stat = b ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(stat).body(id);
    }
}
