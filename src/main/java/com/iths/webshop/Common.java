package com.iths.webshop;

import com.iths.webshop.service.CategoryRepository;
import com.iths.webshop.service.PageRepository;
import com.iths.webshop.Utility.Cart;
import com.iths.webshop.entity.Category;
import com.iths.webshop.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@ControllerAdvice
public class Common {


    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private PageRepository pageRepo;


    @ModelAttribute
    public void sharedData(Model model, HttpSession session, Principal principal) {

        if (principal != null) {
            model.addAttribute("principal", principal.getName());

        }
        List<Page> pages = pageRepo.findAll();
        List<Category> categories = categoryRepo.findAll();

        boolean cartActive = false;
        if (session.getAttribute("cart") != null) {
            Cart cart = (Cart) session.getAttribute("cart");

            int size = cart.size();
            double total = cart.getTotalPrice();

            model.addAttribute("csize", size);
            model.addAttribute("ctotal", total);


            cartActive = true;
        }


        model.addAttribute("cpages", pages);
        model.addAttribute("ccategories", categories);
        model.addAttribute("cartActive", cartActive);


    }
}
