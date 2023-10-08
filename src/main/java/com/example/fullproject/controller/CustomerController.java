package com.example.fullproject.controller;

import com.example.fullproject.dto.*;
import com.example.fullproject.entities.*;
import com.example.fullproject.mapper.BillMapper;
import com.example.fullproject.repository.DeliveryRepository;
import com.example.fullproject.services.impl.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@MultipartConfig
@Controller
@SessionAttributes("cart")
public class CustomerController {
    @Autowired
    private ManufacturerDTOServices manufacturerDTOServices;
    @Autowired
    private ProductDTOServices productDTOServices;
    @Autowired
    private CategoryDTOServices categoryDTOServices;
    @Autowired
    private UserServiceImpl userServices;
    @Autowired
    private DeliveryServiceImpl deliveryService;
    @Autowired
    private BillServiceImpl billService;
    @Autowired
    private DetailDTOServices detailDTOServices;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private BillMapper billMapper;


    //    ---------------------------------------------------LIST---------------------------------------------------

    @GetMapping("/detail-bill-customer")
    public String detailBillCustomer(HttpSession session, Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int pageIndex = page.orElse(1);
        int pageSize = size.orElse(6);
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Set<ProductDto> productDtoPage = productDTOServices.getAll();
        Page<DetailBill> detailBillPage = detailDTOServices.getAllDetail(pageable);
        List<DetailBill> detailBillSet = detailDTOServices.getAllDetail();
        Set<CategoryDTO> categoryDTOList = categoryDTOServices.getAllCategory();
        Set<ManufacturerDto> manufacturerDtoSet = manufacturerDTOServices.getAllManufacturer();
        int totalPage = detailBillPage.getTotalPages();
        UserDetails userDetails = null;
        List<String> roles = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            userDetails = (UserDetails) authentication.getPrincipal();
            List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());
            for (GrantedAuthority authority : authorities
            ) {
                roles.add(authority.getAuthority());
            }
        }


        User user_view = userServiceImpl.findByEmail(userDetails.getUsername());
        session.setAttribute("user", userDetails);
        session.setAttribute("user_view", user_view);
        model.addAttribute("list_manu", manufacturerDtoSet);
        model.addAttribute("list_product", productDtoPage);
        model.addAttribute("list_cate", categoryDTOList);
        model.addAttribute("totalpages", totalPage);
        model.addAttribute("currentpage", pageIndex);
        return "detail_bill_customer";
    }

    @GetMapping("/product")
    public String productPage(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int pageIndex = page.orElse(1);
        int pageSize = size.orElse(6);
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Page<ProductDto> productDtoPage = productDTOServices.getAll(pageable);
        Set<CategoryDTO> categoryDTOList = categoryDTOServices.getAllCategory();
        Set<ManufacturerDto> manufacturerDtoSet = manufacturerDTOServices.getAllManufacturer();
        int totalPage = productDtoPage.getTotalPages();
        model.addAttribute("list_manu", manufacturerDtoSet);
        model.addAttribute("list_product", productDtoPage);
        model.addAttribute("list_cate", categoryDTOList);
        model.addAttribute("totalpages", totalPage);
        model.addAttribute("currentpage", pageIndex);
        return "productList";
    }

    @GetMapping("/productsearch")
    public String productPage1(Model model, @RequestParam("txt_search") String name_search, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int pageIndex = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Page<ProductDto> productDtoPage = null;
        Set<CategoryDTO> categoryDTOList = categoryDTOServices.getAllCategory();
        Set<ManufacturerDto> manufacturerDtoSet = manufacturerDTOServices.getAllManufacturer();

        if (name_search.equals("")) {
            productDtoPage = productDTOServices.getAll(pageable);
        } else {
            productDtoPage = productDTOServices.findByName(name_search, pageable);
        }
        int totalPage = productDtoPage.getTotalPages();
        model.addAttribute("list_manu", manufacturerDtoSet);
        model.addAttribute("list_product", productDtoPage);
        model.addAttribute("list_cate", categoryDTOList);
        model.addAttribute("txt_search", name_search);
        model.addAttribute("totalpages", totalPage);
        model.addAttribute("currentpage", pageIndex);
        return "productList";
    }

    @GetMapping("/detail/{id}")
    public String updateProduct(Model model, @ModelAttribute @PathVariable("id") int id) {
        ProductDto productDto = productDTOServices.findbyId(id).get(0);
        model.addAttribute("product", productDto);
        Set<CategoryDTO> categoryDTOList = categoryDTOServices.getAllCategory();
        Set<ManufacturerDto> manufacturerDtoSet = manufacturerDTOServices.getAllManufacturer();
        model.addAttribute("list_manu", manufacturerDtoSet);
        model.addAttribute("list_cate", categoryDTOList);
        Set<ProductDto> productDtoPage = productDTOServices.getAll();
        model.addAttribute("list_product", productDtoPage);
        return "detailProduct";
    }

    @GetMapping("/category")
    public String catePage(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int pageIndex = page.orElse(1);
        int pageSize = size.orElse(6);
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Set<ProductDto> productDtoPage = productDTOServices.getAll();
        Page<CategoryDTO> categoryDTOList = categoryDTOServices.getAllCategory(pageable);
        Set<ManufacturerDto> manufacturerDtoSet = manufacturerDTOServices.getAllManufacturer();
        int totalPage = categoryDTOList.getTotalPages();
        model.addAttribute("list_manu", manufacturerDtoSet);
        model.addAttribute("list_product", productDtoPage);
        model.addAttribute("list_cate", categoryDTOList);
        model.addAttribute("txt_search", "");
        model.addAttribute("totalpages", totalPage);
        model.addAttribute("currentpage", pageIndex);
        return "categoryList";
    }

    @PostMapping("/categorysearch")
    public String catePage1(Model model, @RequestParam("txt_search") String name_search, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int pageIndex = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Set<ProductDto> productDtoPage = productDTOServices.getAll();
        Page<CategoryDTO> categoryDTOList = null;
        Set<ManufacturerDto> manufacturerDtoSet = manufacturerDTOServices.getAllManufacturer();

        if (name_search.equals("")) {
            categoryDTOList = categoryDTOServices.getAllCategory(pageable);
        } else {
            categoryDTOList = categoryDTOServices.findByName(name_search, pageable);
        }
        int totalPage = categoryDTOList.getTotalPages();
        model.addAttribute("list_manu", manufacturerDtoSet);
        model.addAttribute("list_product", productDtoPage);
        model.addAttribute("list_cate", categoryDTOList);
        model.addAttribute("txt_search", name_search);
        model.addAttribute("totalpages", totalPage);
        model.addAttribute("currentpage", pageIndex);
        return "categoryList";
    }

    @PostMapping("/manufacturersearch")
    public String manuPage1(Model model, @RequestParam("txt_search") String name_search, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int pageIndex = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Page<ManufacturerDto> manufacturerDtos = null;
        Set<CategoryDTO> categoryDTOList = categoryDTOServices.getAllCategory();
        Set<ProductDto> productDtos = productDTOServices.getAll();
        if (name_search.equals("")) {
            manufacturerDtos = manufacturerDTOServices.getAllManufacturer(pageable);
        } else {
            manufacturerDtos = manufacturerDTOServices.findByName(name_search, pageable);
        }
        int totalPage = manufacturerDtos.getTotalPages();
        model.addAttribute("list_manu", manufacturerDtos);
        model.addAttribute("list_product", productDtos);
        model.addAttribute("list_cate", categoryDTOList);
        model.addAttribute("txt_search", "");
        model.addAttribute("totalpages", totalPage);
        model.addAttribute("currentpage", pageIndex);
        return "manuFacturerList";
    }

    @GetMapping("/manufacturer")
    public String manuPage(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int pageIndex = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Page<ManufacturerDto> manufacturerDtos = manufacturerDTOServices.getAllManufacturer(pageable);
        Set<CategoryDTO> categoryDTOList = categoryDTOServices.getAllCategory();
        Set<ProductDto> productDtos = productDTOServices.getAll();
        int totalPage = manufacturerDtos.getTotalPages();
        model.addAttribute("list_manu", manufacturerDtos);
        model.addAttribute("list_product", productDtos);
        model.addAttribute("list_cate", categoryDTOList);
        model.addAttribute("txt_search", "");
        model.addAttribute("totalpages", totalPage);
        model.addAttribute("currentpage", pageIndex);
        return "manuFacturerList";
    }

    //    ---------------------------------------------------SHOPPING CART---------------------------------------------------
    @GetMapping("/shopping-cart")
    public String shoppingCart(HttpSession session, Model model) {
        List<CartDto> cartDtos = (List<CartDto>) session.getAttribute("count");

        Set<Delivery> deliverySet = deliveryService.getAllDelivery();
        long total_price = 0;
        for (CartDto cartDto : cartDtos
        ) {
            total_price += cartDto.getProduct().getPrice() * cartDto.getQuantity();
        }
        model.addAttribute("delivery", deliverySet);
        model.addAttribute("detail_bill", cartDtos);
        model.addAttribute("total_price", total_price);

        return "shopping_cart";
    }

    @PostMapping("/shopping-cart")
    public String payMent(HttpSession session, Model model, @RequestParam("id_delivery") int id_delivery) {
        List<CartDto> detailBills = (List<CartDto>) session.getAttribute("count");

        Optional<Delivery> delivery = deliveryRepository.findById(id_delivery);
        session.removeAttribute("count");

        List<DetailBill> detailBillSet = new ArrayList<>();
        UserDetails userDetails = (UserDetails) session.getAttribute("customer");

        for (CartDto cartDto : detailBills) {
            DetailBill detailBill = new DetailBill().toEntityDetailBill(cartDto);
            DetailBill detailBill1 = new DetailBill(detailBill.getQuantity(),detailBill.getProduct(),detailBill.getBill());
            DetailBill detailBill2 = detailDTOServices.saveDetail(detailBill1);
            detailBillSet.add(detailBill2);
            ProductDto productDto = cartDto.getProduct();
            productDto.setAmount(productDto.getAmount() - cartDto.getQuantity());
            productDTOServices.update(productDto);
        }
        Bill bill = new Bill();
        bill.setDetailBills(detailBillSet);
        if (delivery.isPresent()) {
            bill.setDelivery(delivery.get());
        }
        bill.setDelivery(deliveryRepository.findById(id_delivery).get());
        if (userDetails != null) {
            bill.setUser(userServices.findByEmail(userDetails.getUsername()));
        } else {
            bill.setUser(null);
        }
        bill.setDate_created(new Date());
        String code = detailBillSet.size() + userDetails.getUsername();
        bill.setCode(code);
        Bill bill1 = billService.saveBill1(bill);
        Bill bill2 = bill1;
        for (DetailBill detail : detailBillSet) {
            detail.setBill(bill2);
            detailDTOServices.saveDetail(detail);
        }

        return "redirect:/homepage";
    }

    // chưa add vào config
    @GetMapping("/remove-all-cart")
    public String removeCart(HttpSession session, Model model) {
        List<CartDto> cartDtos = (List<CartDto>) session.getAttribute("count");
        cartDtos.clear();
        int total_price = 0;
        session.setAttribute("count", cartDtos);
        model.addAttribute("carts", cartDtos);
        model.addAttribute("total_price", total_price);
        return "redirect:/shopping-cart";
    }

    @GetMapping("/delete-cart/{id}")
    public String deleteCart(HttpSession session, Model model, @PathVariable("id") long id) {
        List<CartDto> cartDtos = (List<CartDto>) session.getAttribute("count");
        for (CartDto cartdto : cartDtos
        ) {
            if (cartdto.getId() == id) {
                cartDtos.remove(cartdto);
                break;
            }
        }
        int total_price = 0;
        for (CartDto cartDto : cartDtos
        ) {
            total_price += cartDto.getProduct().getPrice() * cartDto.getQuantity();
        }
        session.setAttribute("count", cartDtos);
        model.addAttribute("detail_bill", cartDtos);
        model.addAttribute("total_price", total_price);
        return "redirect:/shopping-cart";
    }

    //----------------------------------------------------------------------------------------------------------------------------
    @GetMapping  ("/add")
    public String addToCart( HttpSession session, Model model ,@RequestParam("id_product") int id,@RequestParam(value = "soluong",defaultValue = "1") int quantity) {
        List<CartDto> cartDtos = (List<CartDto>) session.getAttribute("count");
        long max_card_id = 0l;
        for (CartDto cartDto : cartDtos
        ) {
            max_card_id = Math.max(cartDto.getId(), max_card_id);
        }
        ProductDto productDto = productDTOServices.findbyId(id).get(0);
        CartDto cartDto = new CartDto(max_card_id + 1, quantity, productDto);
        boolean check = true;
        for (CartDto cartDTO1 : cartDtos
        ) {
            if (cartDTO1.getProduct().getName().equals(cartDto.getProduct().getName())) {
                cartDTO1.setQuantity(cartDTO1.getQuantity() + cartDto.getQuantity());
                check = false;
                break;
            }
        }
        if (check) {
            cartDtos.add(cartDto);
        }

        long maxne = cartDtos.size();

        session.setAttribute("tong", maxne);
        session.setAttribute("count", cartDtos);
        model.addAttribute("product", productDto);
        return "redirect:./product";
    }

    @GetMapping("/homepage")
    public String homePage(Model model,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           HttpSession session) {
        List<DetailBill> detailBills = (List<DetailBill>) session.getAttribute("count");
        if (detailBills == null) {
            detailBills = new ArrayList<>();
            session.setAttribute("count", detailBills);
        }
        UserDetails userDetails = null;
        List<String> roles = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            userDetails = (UserDetails) authentication.getPrincipal();
            List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());
            for (GrantedAuthority authority : authorities
            ) {
                roles.add(authority.getAuthority());
            }
        }
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        int pageIndex = page.orElse(1);
        int pageSize = size.orElse(3);
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Page<ProductDto> productDtoPage = productDTOServices.getAll(pageable);
        Set<CategoryDTO> categoryDTOList = categoryDTOServices.getAllCategory();
        Set<ManufacturerDto> manufacturerDtoSet = manufacturerDTOServices.getAllManufacturer();
        int totalPage = productDtoPage.getTotalPages();
        model.addAttribute("list_manu", manufacturerDtoSet);
        model.addAttribute("list_product", productDtoPage);
        model.addAttribute("list_cate", categoryDTOList);
        model.addAttribute("txt_search", "");
        model.addAttribute("totalpages", totalPage);
        model.addAttribute("currentpage", pageIndex);
        session.setAttribute("customer", userDetails);
        session.setAttribute("roles", roles);
        return "home";
    }

    @PostMapping("/search_homepage")
    public String homePage1(Model model,
                            @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size,
                            @RequestParam("txt_search") String name_search,
                            HttpSession session) {
        Page<ProductDto> productDtoPage = null;
        List<DetailBill> detailBills = (List<DetailBill>) session.getAttribute("count");
        if (detailBills == null) {
            detailBills = new ArrayList<>();
            session.setAttribute("count", detailBills);
        }
        UserDetails userDetails = null;
        List<String> roles = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            userDetails = (UserDetails) authentication.getPrincipal();
            List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());
            for (GrantedAuthority authority : authorities
            ) {
                roles.add(authority.getAuthority());
            }
        }
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        int pageIndex = page.orElse(1);
        int pageSize = size.orElse(3);
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);


        if (name_search.equals("")) {
            productDtoPage = productDTOServices.getAll(pageable);
        } else {
            productDtoPage = productDTOServices.findByName(name_search, pageable);
        }
        int totalPage = productDtoPage.getTotalPages();
        model.addAttribute("list_product", productDtoPage);
        model.addAttribute("txt_search", name_search);
        model.addAttribute("totalpages", totalPage);
        model.addAttribute("currentpage", pageIndex);
        session.setAttribute("customer", userDetails);
        session.setAttribute("roles", roles);
        return "/home";
    }
}
