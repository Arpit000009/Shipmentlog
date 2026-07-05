package com.pro.shipment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pro.shipment.entity.PackageEntity;
import com.pro.shipment.enums.PackageType;
import com.pro.shipment.service.PackageEntityService;

@RestController
@RequestMapping("/api/packages")
public class PackageEntityController {
	
	@Autowired
	PackageEntityService packageService;
	
	@GetMapping("/{id}")
	public ResponseEntity<PackageEntity> getPackageById(
	        @PathVariable Long id) {

	    PackageEntity packageEntity =
	            packageService.getPackageById(id);

	    return ResponseEntity.ok(packageEntity);
	}
	
	@GetMapping("/type")
	public ResponseEntity<List<PackageEntity>> getPackagesByPackageType(
	        @RequestParam PackageType packageType) {

	    List<PackageEntity> packages =
	            packageService.getPackagesByPackageType(packageType);

	    return ResponseEntity.ok(packages);
	}
}
