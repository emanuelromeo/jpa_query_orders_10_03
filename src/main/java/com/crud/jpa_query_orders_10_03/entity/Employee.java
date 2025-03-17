package com.crud.jpa_query_orders_10_03.entity;

import com.crud.jpa_query_orders_10_03.entity.abstracts.AuditableEntity;
import com.crud.jpa_query_orders_10_03.enumeration.EmployeeDepartment;
import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "employeeDepartment")
    private EmployeeDepartment department;

    @Column(name = "deleted")
    private Boolean deleted = false;

    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName, EmployeeDepartment department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmployeeDepartment getDepartment() {
        return department;
    }

    public void setDepartment(EmployeeDepartment department) {
        this.department = department;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
