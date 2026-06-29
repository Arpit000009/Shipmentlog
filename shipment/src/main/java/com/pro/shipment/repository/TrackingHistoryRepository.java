package com.pro.shipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.shipment.entity.TrackingHistory;

public interface TrackingHistoryRepository extends JpaRepository<TrackingHistory, Long>{

}
