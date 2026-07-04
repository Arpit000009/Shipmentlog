package com.pro.shipment.entity;

import java.util.List;

import com.pro.shipment.enums.AgentAvailability;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAgent {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String name;
    
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    @Column(unique = true)
    private String phone;
    
    
    @NotBlank
    @Column(unique=true)
    private String vehicleNo;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private AgentAvailability availabilityStatus;
    
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private Double rating;

    @OneToMany(mappedBy="deliveryAgent")
    private List<Shipment> shipments;
}
