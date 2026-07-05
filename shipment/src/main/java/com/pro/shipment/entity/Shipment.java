package com.pro.shipment.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pro.shipment.enums.ShipmentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String trackingNumber;

    private String source;

    private String destination;

    private Double weight;

    private LocalDateTime shipmentDateTime;

    private LocalDate expectedDeliveryDate;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="warehouse_id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name="delivery_agent_id")
    private DeliveryAgent deliveryAgent;

    @OneToOne(mappedBy="shipment",
              cascade=CascadeType.ALL)
    private Payment payment;

    @OneToOne(mappedBy="shipment",
              cascade=CascadeType.ALL)
    private PackageEntity packageEntity;

    @OneToMany(mappedBy="shipment",
               cascade=CascadeType.ALL)
    @JsonIgnore
    private List<TrackingHistory> trackingHistory;
}
