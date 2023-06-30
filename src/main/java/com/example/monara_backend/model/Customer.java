package com.example.monara_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int customer_id;
    private String customer_name;
    private String customer_address;
}
