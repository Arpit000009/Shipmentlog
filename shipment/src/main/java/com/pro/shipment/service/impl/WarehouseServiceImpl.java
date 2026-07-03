package com.pro.shipment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.shipment.entity.Warehouse;
import com.pro.shipment.exception.ResourceAlreadyExistsException;
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
}
