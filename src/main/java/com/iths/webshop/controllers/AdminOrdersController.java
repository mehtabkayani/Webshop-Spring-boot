package com.iths.webshop.controllers;

import com.iths.webshop.service.OrderdetailsRepository;
import com.iths.webshop.service.OrdersRepository;
import com.iths.webshop.service.ProductRepository;
import com.iths.webshop.service.UserRepository;
import com.iths.webshop.tests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrdersController {

    @Autowired
    private OrdersRepository ordersRepo;

    @Autowired
    private OrderdetailsRepository orderdetailsRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductRepository productRepo;


    @GetMapping()
    public String index(Model model) {
        List<Orders> orders = ordersRepo.findAll();
        model.addAttribute("orders", orders);
        return "/admin/orders/index";
    }

    @GetMapping("/orderdetail/{id}")
    public String orderdetail(@PathVariable int id, Model model) {
        List<Orderdetail> orderdetails = orderdetailsRepo.findAll();
        List<Product> product = productRepo.findAll();

        List<Orderdetail> orderdetail = orderdetailsRepo.findByOrderId(id);
        model.addAttribute("orderdetail", orderdetail);

        model.addAttribute("product", product);


        return "/admin/orders/orderdetail";
    }

    @GetMapping("/shipped/{id}")
    public String edit(@PathVariable int id, Model model) {

        Orders order = ordersRepo.getOne(id);
        if(order.getStatus()== null || order.getStatus().equals("Not Shipped")){

            order.setStatus("Shipped");
        }
        ordersRepo.save(order);

        model.addAttribute("order", order);


        return "redirect:/admin/orders";
    }

}
