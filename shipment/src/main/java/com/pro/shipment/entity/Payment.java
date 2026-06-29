package com.pro.shipment.entity;

import jakarta.persistence.GeneratedValue;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDateTime paymentDateTime;

    @OneToOne
    @JoinColumn(name="shipment_id")
    private Shipment shipment;
}