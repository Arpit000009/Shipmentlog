package com.pro.shipment.entity;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private Integer capacity;

    @Column(unique=true)
    private String contactNo;

    @OneToMany(mappedBy="warehouse")
    private List<Shipment> shipments;
}
