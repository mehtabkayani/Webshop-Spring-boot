package com.iths.webshop.controllers;

import com.iths.webshop.service.PageRepository;
import com.iths.webshop.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/pages")
public class AdminPagesController {

    @Autowired
    private PageRepository pageRepo;

    @GetMapping
    public String index(Model model) {
        List<Page> pages = pageRepo.findAll();
        model.addAttribute("pages", pages);

        return "admin/pages/index";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute Page page) {
        return "admin/pages/add";
    }

    @PostMapping("/add")
    public String add(@Valid Page page, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        if (bindingResult.hasErrors()) {
            return "admin/pages/add";
        }
        redirectAttributes.addFlashAttribute("message", "Page added");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");


        String url = page.getUrl() == "" ? page.getTitle().toLowerCase().replace(" ", "-") :
                page.getUrl().toLowerCase().replace(" ", "-");

        Page urlExists = pageRepo.findByUrl(url);

        if (urlExists != null) {
            redirectAttributes.addFlashAttribute("message", "Url already exists, choose another");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        } else {
            page.setUrl(url);
            pageRepo.save(page);
        }

        return "redirect:/admin/pages/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Page page = pageRepo.getOne(id);
        model.addAttribute("page", page);

        return "admin/pages/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid Page page, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        Page pageCurrent = pageRepo.getOne(page.getId());

        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", pageCurrent.getTitle());
            return "admin/pages/edit";
        }
        redirectAttributes.addFlashAttribute("message", "Page edited");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");


        String url = page.getUrl() == "" ? page.getTitle().toLowerCase().replace(" ", "-") :
                page.getUrl().toLowerCase().replace(" ", "-");

        Page urlExists = pageRepo.findByUrlAndIdNot(url, page.getId());

        if (urlExists != null) {
            redirectAttributes.addFlashAttribute("message", "Url already exists, choose another");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            redirectAttributes.addFlashAttribute("page", page);
        } else {
            page.setUrl(url);
            pageRepo.save(page);
        }

        return "redirect:/admin/pages/edit/" + page.getId();
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("message", "Page deleted");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");


        pageRepo.deleteById(id);

        return "redirect:/admin/pages";
    }
}
