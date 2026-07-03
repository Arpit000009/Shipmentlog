package com.pro.shipment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.shipment.entity.Warehouse;
import com.pro.shipment.service.WarehouseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(
            @Valid @RequestBody Warehouse warehouse) {

        Warehouse savedWarehouse = warehouseService.createWarehouse(warehouse);

        return new ResponseEntity<>(savedWarehouse, HttpStatus.CREATED);
    }
}
