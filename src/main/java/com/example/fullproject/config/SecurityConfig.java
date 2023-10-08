package com.example.fullproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class SecurityConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/homepage").setViewName("home");
        registry.addViewController("/login").setViewName("/users/login");
        registry.addViewController("/register").setViewName("/users/register");
        registry.addViewController("/product").setViewName("productList");
        registry.addViewController("/detail/**").setViewName("detailProduct");
        registry.addViewController("/category").setViewName("categoryList");
        registry.addViewController("/manufacturer").setViewName("manuFacturerList");
        registry.addViewController("/shopping-cart").setViewName("shopping_cart");
        //registry.addViewController("/add/**").setViewName("add-product");
        registry.addViewController("/employee/listmanufacturer").setViewName("./employee/listManufacturer");
        registry.addViewController("/employee/listcategory").setViewName("./employee/listCategory");
        registry.addViewController("/employee/listproduct").setViewName("./employee/listProduct");
        registry.addViewController("/employee/addmanufacturer").setViewName("./employee/addManufacturer");
        registry.addViewController("/employee/addcategory").setViewName("./employee/addCategory");
        registry.addViewController("/employee/addproduct").setViewName("./employee/addProduct");
        registry.addViewController("/employee/updatemanu/**").setViewName("./employee/editManufacturer");
        registry.addViewController("/employee/updatecate/**").setViewName("./employee/editCategory");
        registry.addViewController("/employee/update/**").setViewName("./employee/editProduct");
        registry.addViewController("/employee/delete/**").setViewName("/employee/listproduct");
        registry.addViewController("/employee/delete-bill/**").setViewName("/employee/bill");
        registry.addViewController("/employee/delete-detailbill/**").setViewName("/employee/detailbill");
        registry.addViewController("/employee/bill").setViewName("/employee/billing");
        registry.addViewController("/employee/customerlist").setViewName("/employee/listCustomer");
        registry.addViewController("/404").setViewName("404");
        registry.addViewController("/imgs/**").setViewName("img");
        registry.addViewController("/detail-bill-customer/**").setViewName("./detailcustomer");

        registry.addViewController("/employee/updateuser/**").setViewName("./employee/updateUser");


    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/btrap/**", "/assets/**","/assets/**")
                .addResourceLocations("classpath:/btrap/", "classpath:/static/", "classpath:/assets/");
    }
}
