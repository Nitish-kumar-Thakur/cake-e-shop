package com.onlinebackery.controller;

import com.onlinebackery.entity.Product;
import com.onlinebackery.global.GlobalData;
import com.onlinebackery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") Long id){
        GlobalData.cart.add(productService.getProductById(id));
        return "redirect:/shop";
    }
    @GetMapping("/cart")
    public String  getCart(Model model){
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart",GlobalData.cart);
        return "cart";
    }
    @GetMapping("/cart/removeItem/{id}")
    public String removeProduct(@PathVariable("id") int id){
        GlobalData.cart.remove(id);
        return "redirect:/cart";
    }
    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }



}
