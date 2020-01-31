package com.iths.webshop.entity;

import com.iths.webshop.Utility.Cart;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "orderdetail")
public class Orderdetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;
    private double price;

    //@ManyToOne
    //Orders orders;
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "product_id")
    private int productId;

    public static Iterable<? extends Orderdetail> convert(Cart cart, Orders order) {
       Map<Product,Integer> detailList = cart.getProducts();

   return  detailList.entrySet().stream().map(d -> {

        Orderdetail orderdetail = new Orderdetail();
        orderdetail.setOrderId(order.getId());
        orderdetail.setProductId(d.getKey().getId());
        orderdetail.setQuantity(d.getValue());
        orderdetail.setPrice(d.getKey().getPrice());
        return orderdetail;
     }).collect(Collectors.toList());


    }

    //@ManyToOne
    //Product product;
}
