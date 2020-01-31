package com.iths.webshop.controllers;

import com.iths.webshop.Utility.Cart;
import com.iths.webshop.service.OrderdetailsRepository;
import com.iths.webshop.service.OrdersRepository;
import com.iths.webshop.service.ProductRepository;
import com.iths.webshop.service.UserRepository;
import com.iths.webshop.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OrdersRepository ordersRepo;

    @Autowired
    private OrderdetailsRepository orderdetailsRepo;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/add/{id}")
    public String add(@PathVariable int id,
                      HttpSession session, Model model,
                      @RequestParam(value = "cartPage", required = false) String cartPage) {

        Product product = productRepo.getOne(id);
        Cart cart;
        if (session.getAttribute("cart") == null) {
            cart = new Cart();
        } else {
            cart = (Cart) session.getAttribute("cart");
        }
        cart.add(product);
        session.setAttribute("cart", cart);

        int size = cart.size();
        double total = cart.getTotalPrice();

        model.addAttribute("size", size);
        model.addAttribute("total", total);
        if (cartPage != null) {
            return "redirect:/cart/view";
        }

        return "cart_view";
    }

    @GetMapping("/subtract/{id}")
    public String subtract(@PathVariable int id, HttpSession session, Model model, HttpServletRequest httpServletRequest) {
        Product product = productRepo.getOne(id);
        Cart cart = (Cart) session.getAttribute("cart");

        cart.removeOne(product);
            session.removeAttribute("cart");

        if (cart.size() > 0) {
            session.setAttribute("cart", cart);
        }

        String refererLink = httpServletRequest.getHeader("referer");

        return "redirect:" + refererLink;
    }

    @GetMapping("view")
    public String view(HttpSession session, Model model) {
        if (session.getAttribute("cart") == null) {
            return "redirect:/";
        }

        Cart cart = (Cart) session.getAttribute("cart");
        model.addAttribute("cart", cart);
        model.addAttribute("notCartViewPage", true);

        return "cart";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        model.addAttribute("cart", cart);
        model.addAttribute("notCartViewPage", true);
        return "checkout";
    }

    @GetMapping("/completed")
    public String completed(HttpSession session, Model model, Principal principal) {
        User user = userRepo.findByUsername(principal.getName());
        Cart cart = (Cart) session.getAttribute("cart");
        model.addAttribute("cart", cart);

        Orders order = new Orders();


        order.setUserId(user.getId());
        order.setStatus("Not Shipped");
       order = ordersRepo.save(order);

        for (Orderdetail detail : Orderdetail.convert(cart,order)) {

            orderdetailsRepo.save(detail);
        }


        session.removeAttribute("cart");
        return "completed";

    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable int id, HttpSession session, Model model, HttpServletRequest httpServletRequest) {
        Cart cart = (Cart) session.getAttribute("cart");
        Product product = productRepo.getOne(id);
        cart.removeAll(product);

        if (cart.size() == 0) {
            session.removeAttribute("cart");
        }else {

        session.setAttribute("cart", cart);
        }
        String referalLink = httpServletRequest.getHeader("referer");
        return "redirect:" + referalLink;

    }

    @GetMapping("/clear")
    public String clear(HttpSession session, HttpServletRequest httpServletRequest) {
        session.removeAttribute("cart");

        String referalLink = httpServletRequest.getHeader("referer");

        return "redirect:" + referalLink;


    }
}
