package com.iths.webshop.tests;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //  @ManyToOne
    // User user;
    @Column(name = "user_id")
    private int userId;

    private String status = "Not Shipped";


   // @OneToMany
    //List<Orderdetail> orderdetail;
}
