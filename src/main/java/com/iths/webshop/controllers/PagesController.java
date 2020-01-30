package com.iths.webshop.controllers;

import com.iths.webshop.service.PageRepository;
import com.iths.webshop.tests.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PagesController {

    @Autowired
    private PageRepository pageRepo;

    @GetMapping
    public String index(Model model) {
        Page page = pageRepo.findByUrl("home");
        model.addAttribute("page", page);
        return "page";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/{url}")
    public String page(@PathVariable String url, Model model) {
        Page page = pageRepo.findByUrl(url);
        if (page == null) {
            return "redirect:/";
        }
        model.addAttribute("page", page);
        return "page";
    }
}
