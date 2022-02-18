package com.example.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
@AttributeOverride(name = "id", column = @Column(name = "order_id"))
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(name = "ORDER_ITEM")
    private String item;
    @Column(name = "ORDER_ADDRESS")
    private String address;
    @Column(name = "ORDER_PRICE")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "personId", referencedColumnName = "person_id")
    private Person person;

    public Person getPerson() {
        return person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
