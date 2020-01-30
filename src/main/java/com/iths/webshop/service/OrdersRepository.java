package com.iths.webshop.service;

import com.iths.webshop.tests.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    Orders getByUserId(int id);
}
