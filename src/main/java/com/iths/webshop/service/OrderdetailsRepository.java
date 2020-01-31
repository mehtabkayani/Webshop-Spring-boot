package com.iths.webshop.service;

import com.iths.webshop.entity.Orderdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderdetailsRepository extends JpaRepository<Orderdetail, Integer> {

    List<Orderdetail> findByOrderId(int orderId);
}
