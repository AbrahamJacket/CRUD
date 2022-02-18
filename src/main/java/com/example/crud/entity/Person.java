package com.example.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PERSONS")
@AttributeOverride(name = "id", column = @Column(name = "person_id"))
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(name = "PERSON_FIRST_NAME")
    private String firstName;
    @Column(name = "PERSON_LAST_NAME")
    private String lastName;
    private String cardNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private Set<Order> orders = new HashSet<>();

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
}
