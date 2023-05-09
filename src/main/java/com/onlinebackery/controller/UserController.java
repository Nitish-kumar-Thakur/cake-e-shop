package com.onlinebackery.controller;

import com.onlinebackery.global.GlobalData;
import com.onlinebackery.service.CategoryService;
import com.onlinebackery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    @Autowired
    CategoryService cservice;
    @Autowired
    ProductService pservice;

    @GetMapping({"/","/home"})
    public String getHome(Model model)
    {
        model.addAttribute("cartCount",GlobalData.cart.size());
        return "index";
    }

    @GetMapping("/shop")
    public String getShop(Model model){
        model
                .addAttribute("categories",cservice.getAll())
                .addAttribute("products",pservice.getAll());
        model.addAttribute("cartCount",GlobalData.cart.size());
        return "shop";
    }
    @GetMapping("/shop/category/{id}")
    public String getProductByCategories(@PathVariable("id") int id, Model model) {
        model
                .addAttribute("categories", cservice.getAll())
                .addAttribute("products", pservice.getAllByCategorisId(id));
        model.addAttribute("cartCount",GlobalData.cart.size());
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String getViewProduct(@PathVariable("id") Long id,Model model){
        model.addAttribute("product",pservice.getProductById(id));
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "viewProduct";
    }

}
