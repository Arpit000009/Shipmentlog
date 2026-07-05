package com.pro.shipment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pro.shipment.enums.PackageType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PackageType packageType;

    private boolean fragile;

    private String dimensions;
    
    @JsonIgnore
    @OneToOne
    @JoinColumn(name="shipment_id")
    private Shipment shipment;
}
