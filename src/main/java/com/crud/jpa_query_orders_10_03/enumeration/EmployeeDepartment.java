package com.crud.jpa_query_orders_10_03.enumeration;

public enum EmployeeDepartment {
    HR("Human Resources"),
    FINANCE("Finance"),
    MARKETING("Marketing"),
    SALES("Sales"),
    IT("Information Technology");

    private final String description;

    EmployeeDepartment(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
