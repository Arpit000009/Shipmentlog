package com.pro.shipment.entity;

@Entity
public class TrackingHistory {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String location;

    private String remarks;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name="shipment_id")
    private Shipment shipment;
}