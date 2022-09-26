package com.validation.assignment.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Table(name = "users")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private Date birthDate;

    @Column(length = 10, nullable = false)
    private String gender;

    @Column(nullable=false)
    private int salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}

