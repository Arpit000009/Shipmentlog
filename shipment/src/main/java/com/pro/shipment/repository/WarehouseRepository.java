package com.pro.shipment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.shipment.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long>{
	boolean existsByContactNo(String contactNo);
	List<Warehouse> findByLocation(String location);
	List<Warehouse> findByCapacityGreaterThan(Integer capacity);
}
