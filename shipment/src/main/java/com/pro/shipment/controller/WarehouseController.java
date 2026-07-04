package com.pro.shipment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {

        List<Warehouse> warehouses = warehouseService.getAllWarehouses();

        return ResponseEntity.ok(warehouses);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {

        Warehouse warehouse = warehouseService.getWarehouseById(id);

        return ResponseEntity.ok(warehouse);
    }
    
    @GetMapping("/location")
    public ResponseEntity<List<Warehouse>> getWarehouseByLocation(
            @RequestParam String location) {

        List<Warehouse> warehouses =
                warehouseService.getWarehouseByLocation(location);

        return ResponseEntity.ok(warehouses);
    }
    
    @GetMapping("/capacity")
    public ResponseEntity<List<Warehouse>> getWarehouseByCapacity(
            @RequestParam Integer capacity) {

        List<Warehouse> warehouses =
                warehouseService.getWarehouseByCapacity(capacity);

        return ResponseEntity.ok(warehouses);
    }
}
