package com.iths.webshop.restcontroller;

import com.iths.webshop.service.*;
import com.iths.webshop.entity.Orderdetail;
import com.iths.webshop.entity.Orders;
import com.iths.webshop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "*")
public class AdminRestController {

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private OrdersRepository ordersRepo;

    @Autowired
    private OrderdetailsRepository orderdetailsRepo;

    @GetMapping("/orders")
    public List<Orders> orders(){
       List<Orders> orders = ordersRepo.findAll();
       return orders;
    }

    @PutMapping("/order/{id}")
    public Orders updateOrder(@RequestBody Orders order1,@PathVariable int id){
       return ordersRepo.findById(id).map(o -> {
            o.setStatus(order1.getStatus());
            return ordersRepo.save(o);
        }).orElseGet(()->{
           order1.setId(id);
           return ordersRepo.save(order1);
        });
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable int id){
        List<Orderdetail> orderdetails = orderdetailsRepo.findAll();
        for (Orderdetail orderD: orderdetails) {
            if(orderD.getOrderId()==id){
                orderdetailsRepo.delete(orderD);
            }
        }
        ordersRepo.deleteById(id);
    }


    @GetMapping("/orderdetails")
    public List<Orderdetail> orderdetails(){
        return orderdetailsRepo.findAll();
    }


    @GetMapping("/product")
    public List<Product> products() {
        return productRepo.findAll();
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {

        return productRepo.save(product);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@RequestBody Product product1, @PathVariable int id) {
        Product product = productRepo.getOne(id);

        product.setName(product1.getName());
        product.setDescription(product1.getDescription());
        product.setCategoryId(product1.getCategoryId());
        product.setUrl(product1.getUrl());
        product.setPrice(product1.getPrice());
        return productRepo.save(product);
    }

    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable int id) {

        productRepo.deleteById(id);
    }

}
