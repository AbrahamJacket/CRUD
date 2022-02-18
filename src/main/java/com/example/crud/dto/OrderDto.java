package com.example.crud.dto;

import com.example.crud.entity.Order;

public class OrderDto {
    private String item;
    private String address;
    private Integer price;
    private String firstName;
    private String lastName;

    public OrderDto(Long id, String item, String address, Integer price, String firstName, String lastName) {
        this.item = item;
        this.address = address;
        this.price = price;
        this.firstName = firstName;
        this.lastName = lastName;
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

    static public OrderDto adaptToOrderDto(Order order){
        return new OrderDto(order.getId(), order.getItem(), order.getAddress(), order.getPrice(),
                order.getPerson().getFirstName(), order.getPerson().getLastName());
    }
}
