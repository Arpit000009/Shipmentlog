package com.pro.shipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.shipment.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long>{

}
