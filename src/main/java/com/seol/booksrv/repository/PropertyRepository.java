package com.seol.booksrv.repository;

import com.seol.booksrv.entity.Block;
import com.seol.booksrv.entity.Property;
import com.seol.booksrv.entity.types.BlockType;
import com.seol.booksrv.exception.WrongWebUserArgumentException;
import com.seol.booksrv.utils.DateRangeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
@Slf4j
public class PropertyRepository {

    private final static Map<Integer, Property> properties = new HashMap<>();
    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    static {
        Property value = new Property(1, "castle", new ArrayList<>());
        properties.put(1, value);
    }

    //TODO for read/write methods - implement immutability of Property and Block
    public Optional<Property> findById(Integer id) {
        readLock.lock();
        try {
            return Optional.ofNullable(properties.get(id));
        } finally {
            readLock.unlock();
        }
    }

    public Optional<Block> findBlockById(UUID blockId) {
        return getBlockByType(blockId, BlockType.BLOCK);
    }

    public Optional<Block> findBlockById(Integer propertyId, UUID blockId) {
        return getBlockByType(propertyId, blockId, BlockType.BLOCK);
    }

    public Optional<Block> findBookingById(UUID blockId) {
        return getBlockByType(blockId, BlockType.BOOKING);
    }

    private Optional<Block> getBlockByType(UUID blockId, BlockType type) {
        readLock.lock();
        try {
            return properties.values().stream().flatMap(p -> p.getBlocks().stream()).filter(
                    b -> b.getType() == type && blockId.equals(b.getBlockId())).findFirst();
        } finally {
            readLock.unlock();
        }
    }

    private Optional<Block> getBlockByType(Integer propertyId, UUID blockId, BlockType type) {
        readLock.lock();
        try {
            Property property = properties.get(propertyId);
            if (property != null) {
                return property.getBlocks().stream().filter(
                        b -> b.getType() == type && blockId.equals(b.getBlockId())).findFirst();
            } else {
                return Optional.empty();
            }
        } finally {
            readLock.unlock();
        }
    }

    public void addBlock(Integer id, Block block) {
        writeLock.lock(); // global locking of "whole" table
        try {
            Property property = properties.get(id);
            if (property == null) {
                throw new WrongWebUserArgumentException("no such property exists: " + id);
            }

            Optional<Block> first = property.getBlocks().stream().filter(bl ->
                    !bl.isCancelled()
                            && (DateRangeUtils.startDateIsInRange(bl.getStartDate(), bl.getEndDate(), block.getStartDate())
                            || DateRangeUtils.endDateIsInRange(bl.getStartDate(), bl.getEndDate(), block.getEndDate()))).findFirst();

            if (first.isPresent()) {
                throw new WrongWebUserArgumentException("This range is already busy");
            }

            property.getBlocks().add(block);
            block.setProperty(property);
            block.setBlockId(UUID.randomUUID());

        } finally {
            writeLock.unlock();
        }
    }

    public Block updateBlock(Integer id, Block block) {
        writeLock.lock(); // global locking of "whole" table
        try {
            Property property = properties.get(id);
            if (property == null) {
                throw new WrongWebUserArgumentException("no such property exists: " + id);
            }

            Optional<Block> first = property.getBlocks().stream().filter(bl ->
                    !bl.getBlockId().equals(block.getBlockId()) &&
                    !bl.isCancelled()
                            && (DateRangeUtils.startDateIsInRange(bl.getStartDate(), bl.getEndDate(), block.getStartDate())
                            || DateRangeUtils.endDateIsInRange(bl.getStartDate(), bl.getEndDate(), block.getEndDate()))).findFirst();

            if (first.isPresent()) {
                throw new WrongWebUserArgumentException("This range is already busy");
            }

            Block stored = getBlockByType(id, block.getBlockId(), block.getType()).get();

            stored.setCancelled(block.isCancelled());
            stored.setReason(block.getReason());
            stored.setGuestDetails(block.getGuestDetails());
            stored.setStartDate(block.getStartDate());
            stored.setEndDate(block.getEndDate());

            return stored;
        } finally {
            writeLock.unlock();
        }
    }

    public boolean deleteBlock(UUID blockId) {
        writeLock.lock(); // global locking of "whole" table
        try {
            Optional<Block> blockOpt = properties.values().stream().flatMap(p -> p.getBlocks().stream())
                    .filter(b -> b.getBlockId().equals(blockId)).findFirst();
            if (blockOpt.isPresent()) {
                Block block = blockOpt.get();
                Property property = properties.get(block.getProperty().getPropertyId());
                property.getBlocks().remove(block);
            }

            return blockOpt.isPresent();

        } finally {
            writeLock.unlock();
        }
    }


}
