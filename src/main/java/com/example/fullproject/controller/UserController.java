package com.example.fullproject.controller;

import com.example.fullproject.dto.CategoryDTO;
import com.example.fullproject.dto.ManufacturerDto;
import com.example.fullproject.dto.ProductDto;
import com.example.fullproject.dto.UserDto;
import com.example.fullproject.entities.DetailBill;
import com.example.fullproject.services.UserServices;
import com.example.fullproject.services.impl.CategoryDTOServices;
import com.example.fullproject.services.impl.ManufacturerDTOServices;
import com.example.fullproject.services.impl.ProductDTOServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private ManufacturerDTOServices manufacturerDTOServices;
    @Autowired
    private ProductDTOServices productDTOServices;
    @Autowired
    private CategoryDTOServices categoryDTOServices;
    @Autowired
    private UserServices userDtoServices;

    @GetMapping("/demo")
    public String demo(Model model ) {
        Set<ProductDto> productDtoPage = productDTOServices.getAll();
        model.addAttribute("list_product", productDtoPage);
        return "./employee/demo";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "./users/register";
    }

    @PostMapping("/register")
    public String save(@ModelAttribute UserDto userDto) {
        userDtoServices.saveUser(userDto);
        return "redirect:/homepage";
    }

    @GetMapping("/login")
    public String login() {
        return "./users/login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        SecurityContextHolder.clearContext();
        session.removeAttribute("user");
        session.removeAttribute("roles");
        return "redirect:/homepage";
    }

}
