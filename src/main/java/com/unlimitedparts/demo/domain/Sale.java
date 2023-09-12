package com.unlimitedparts.demo.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long id;
    @Column(name = "date_from")
    private LocalDate from;
    @Column(name = "date_to")
    private LocalDate to;
    @Column(name = "sale_percent")
    private Double percentage;

    @OneToMany(mappedBy = "sale")
    private List<Product> products;

    public Sale(){}

    public Long getSaleId() {
        return id;
    }

    public void setSaleId(Long id) {
        this.id = id;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
