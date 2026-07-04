package com.pro.shipment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.shipment.entity.Warehouse;
import com.pro.shipment.exception.ResourceAlreadyExistsException;
import com.pro.shipment.exception.ResourceNotFoundException;
import com.pro.shipment.repository.WarehouseRepository;
import com.pro.shipment.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {

        if (warehouseRepository.existsByContactNo(warehouse.getContactNo())) {
            throw new ResourceAlreadyExistsException("Warehouse contact number already exists.");
        }

        return warehouseRepository.save(warehouse);
    }
    
    @Override
    public List<Warehouse> getAllWarehouses() {

        return warehouseRepository.findAll();
    }
    
    @Override
    public Warehouse getWarehouseById(Long id) {

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);

        if (optionalWarehouse.isPresent()) {
            return optionalWarehouse.get();
        } else {
            throw new ResourceNotFoundException("Warehouse not found with id : " + id);
        }
    }
    
    @Override
    public List<Warehouse> getWarehouseByLocation(String location) {

        List<Warehouse> warehouses = warehouseRepository.findByLocation(location);

        if (warehouses.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No warehouse found at location : " + location);
        }

        return warehouses;
    }
    
    @Override
    public List<Warehouse> getWarehouseByCapacity(Integer capacity) {

        List<Warehouse> warehouses =
                warehouseRepository.findByCapacityGreaterThan(capacity);

        if (warehouses.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No warehouse found with capacity greater than " + capacity);
        }

        return warehouses;
    }
}
