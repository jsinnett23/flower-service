package edu.iu.jsinnett.finalproject.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "flowers", name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private int id;
    private int customerId;  // This should be a foreign key linking to the Customer table
    private int flowerId;    // This should be a foreign key linking to the Flower table
    private String status;   // e.g., submitted, processed, delivered

    public Order() {}

    public Order(int id, int customerId, int flowerId, String status) {
        this.id = id;
        this.customerId = customerId;
        this.flowerId = flowerId;
        this.status = status;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(int flowerId) {
        this.flowerId = flowerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
