package com.onlinebackery.controller;

import com.onlinebackery.entity.Role;
import com.onlinebackery.entity.User;
import com.onlinebackery.global.GlobalData;
import com.onlinebackery.repository.RoleRepository;
import com.onlinebackery.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String getLogin()
    {
        GlobalData.cart.clear();
        return "login";
    }
    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }
    @PostMapping("/register")
    public String postRegister(@ModelAttribute User user, HttpServletRequest http) throws ServletException{
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2).get());
        user.setRole(roles);
        userRepository.save(user);
        http.login(user.getEmail(),password);
        return "redirect:/";

    }



}
