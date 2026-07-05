package com.pro.shipment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.shipment.entity.PackageEntity;
import com.pro.shipment.enums.PackageType;

public interface PackageEntityRepository extends JpaRepository<PackageEntity,Long>{
	
	List<PackageEntity> findByPackageType(PackageType packageType);

    List<PackageEntity> findByFragile(boolean fragile);
    


}
