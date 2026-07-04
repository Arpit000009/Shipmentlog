package com.pro.shipment.service;

import java.util.List;

import com.pro.shipment.entity.Warehouse;

public interface WarehouseService {
	Warehouse createWarehouse(Warehouse warehouse);
	List<Warehouse> getAllWarehouses();
	Warehouse getWarehouseById(Long id);
	List<Warehouse> getWarehouseByLocation(String location);
	List<Warehouse> getWarehouseByCapacity(Integer capacity);
	
	Warehouse updateWarehouse(Long id, Warehouse warehouse);

	void deleteWarehouse(Long id);
}
