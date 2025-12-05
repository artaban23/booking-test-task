package com.seol.booksrv.service;

import com.seol.booksrv.dto.BlockDto;
import com.seol.booksrv.dto.CreateBlockRequest;
import com.seol.booksrv.entity.Block;
import com.seol.booksrv.exception.WrongWebUserArgumentException;
import com.seol.booksrv.mapper.BlockMapper;
import com.seol.booksrv.repository.PropertyRepository;
import com.seol.booksrv.utils.DateRangeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlockService {

    private final PropertyRepository propertyRepository;
    private final BlockMapper blockMapper;

    public BlockDto getBlockById(UUID blockId) {
        log.info("Fetching block by id:  {}", blockId);

        Optional<Block> blockById = propertyRepository.findBlockById(blockId);
        BlockDto blockDto = blockById.map(blockMapper::toDto)
                .orElseThrow(() -> new WrongWebUserArgumentException("Missing block: " + blockId));

        return blockDto;
    }

    public BlockDto createBlock(CreateBlockRequest request) {
        log.info("Creating block for property: {}", request.propertyId());
        Block block = blockMapper.toEntity(request);
        DateRangeUtils.validateDateRangeBasic(block.getStartDate(), block.getEndDate());
        propertyRepository.addBlock(request.propertyId(), block);
        log.info("Block created with id: {}", block.getBlockId());
        return blockMapper.toDto(block);
    }

    public BlockDto updateBlock(BlockDto blockDto) {
        log.info("Updating block with id: {}", blockDto.blockId());
        DateRangeUtils.validateDateRangeBasic(blockDto.startDate(), blockDto.endDate());

        Block entity = blockMapper.toEntity(blockDto);
        Block updated = propertyRepository.updateBlock(blockDto.propertyId(), entity);

        log.info("Updated block with id: {}", blockDto.blockId());
        return blockMapper.toDto(updated);
    }

    public boolean deleteBlock(UUID id) {
        log.info("Deleting block with id: {}", id);
        boolean b = propertyRepository.deleteBlock(id);
        if (b) {
            log.info("Deleted block with id: {}", id);
        } else {
            log.info("block is already deleted: {}", id);
        }
        return b;
    }

}
