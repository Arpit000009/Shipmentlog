package com.pro.shipment.entity;

@Entity
public class PackageEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PackageType packageType;

    private boolean fragile;

    private String dimensions;

    @OneToOne
    @JoinColumn(name="shipment_id")
    private Shipment shipment;
}
