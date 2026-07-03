package com.pro.shipment.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String name;
    
    @Email
    @Column(unique=true)
    private String email;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    @Column(unique=true)
    private String phone;

    @NotBlank
    private String address;

    @OneToMany(mappedBy="customer")
    private List<Shipment> shipments;
}