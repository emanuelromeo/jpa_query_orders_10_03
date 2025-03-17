package com.crud.jpa_query_orders_10_03.entity;

import com.crud.jpa_query_orders_10_03.entity.abstracts.AuditableEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
public class Customer extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name = "name")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "registrationDate")
    private LocalDate registrationDate;

    @Column(name = "deleted")
    private Boolean deleted = false;

    public Customer() {
    }

    public Customer(Long customerId, String name, LocalDate registrationDate) {
        this.customerId = customerId;
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
