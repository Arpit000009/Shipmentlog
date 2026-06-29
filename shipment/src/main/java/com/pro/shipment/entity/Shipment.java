package com.pro.shipment.entity;

@Entity
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
    private List<TrackingHistory> trackingHistory;
}
