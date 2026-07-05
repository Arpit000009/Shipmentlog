package com.pro.shipment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.shipment.entity.PackageEntity;
import com.pro.shipment.entity.Shipment;
import com.pro.shipment.enums.PackageType;
//import com.pro.shipment.enums.ShipmentStatus;

public interface PackageEntityRepository extends JpaRepository<PackageEntity,Long>{
	
	List<PackageEntity> findByPackageType(PackageType packageType);

    List<PackageEntity> findByFragile(boolean fragile);
    
//    List<Shipment> findByStatus(ShipmentStatus status);

}
