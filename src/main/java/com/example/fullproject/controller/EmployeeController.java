package com.example.fullproject.controller;

import com.example.fullproject.dto.*;
import com.example.fullproject.entities.*;
import com.example.fullproject.repository.DetailBillRepository;
import com.example.fullproject.repository.UserRepository;
import com.example.fullproject.services.impl.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@MultipartConfig
@Controller
//@RequestMapping("/employee")
public class EmployeeController {
    @Value("${demo.upload.file}")
    private String path_file;

    @Autowired
    private ProductDTOServices productDTOServices;
    @Autowired
    private ManufacturerDTOServices manufacturerDTOServices;
    @Autowired
    private CategoryDTOServices categoryDTOServices;
    @Autowired
    private StatusDTOServices statusDTOServices;
    @Autowired
    private BillServiceImpl billService;
    @Autowired
    private  DetailDTOServices detailDTOServices;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private DetailBillRepository detailBillRepository;
    @GetMapping("/employee/dashboard")
    public String dashboard(Model model , HttpSession session){
        UserDetails userDetails= null;
        List<String> roles = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)){
            userDetails = (UserDetails) authentication.getPrincipal();
            List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());
            for (GrantedAuthority authority : authorities
            ) {
                roles.add(authority.getAuthority());
            }
        }
        User user_view =  userServiceImpl.findByEmail(userDetails.getUsername());
        session.setAttribute("user",userDetails);
        session.setAttribute("user_view",user_view);
        return "./employee/detailAccount";
    }
    //------------------------------LIST------------------------------
    @GetMapping("/employee/customerlist")
    public String userList(HttpSession session,Model model  ,@RequestParam("page")Optional<Integer>page,@RequestParam("size")Optional<Integer>size){
        int pageIndex = page.orElse(1);
        int pageSize =size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<User> userPage = userServiceImpl.findAllUser(pageable);
        int totalPage = userPage.getTotalPages();
        UserDetails userDetails= null;
        List<String> roles = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)){
            userDetails = (UserDetails) authentication.getPrincipal();
            List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());
            for (GrantedAuthority authority : authorities
            ) {
                roles.add(authority.getAuthority());
            }
        }
        List<Bill> billList = billService.getAllBills();
        session.setAttribute("user",userDetails);
        model.addAttribute("bill_list",billList);
        model.addAttribute("user_list",userPage);
        model.addAttribute("totalpages",totalPage);
        model.addAttribute("currentpage",pageIndex);
        return "./employee/listCustomer";
    }
    @GetMapping("/employee/detailbill")
    public String detailBill(HttpSession session,Model model  ,@RequestParam("page")Optional<Integer>page,@RequestParam("size")Optional<Integer>size){
        int pageIndex = page.orElse(1);
        int pageSize =size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<DetailBill> detailBillPage = detailDTOServices.getAllDetail(pageable);
        int totalPage = detailBillPage.getTotalPages();
        UserDetails userDetails= null;
        List<String> roles = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)){
            userDetails = (UserDetails) authentication.getPrincipal();
            List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());
            for (GrantedAuthority authority : authorities
            ) {
                roles.add(authority.getAuthority());
            }
        }
        session.setAttribute("user",userDetails);
        model.addAttribute("detailbill_list",detailBillPage);
        model.addAttribute("totalpages",totalPage);
        model.addAttribute("currentpage",pageIndex);
        return "./employee/listFinishDetailBill";
    }
    @GetMapping("/employee/bill")
    public String bill(HttpSession session,Model model  ,@RequestParam("page")Optional<Integer>page,@RequestParam("size")Optional<Integer>size){
        int pageIndex = page.orElse(1);
        int pageSize =size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<Bill> billPage = billService.getAllBills(pageable);
        int totalPage = billPage.getTotalPages();
        UserDetails userDetails= null;
        List<String> roles = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)){
            userDetails = (UserDetails) authentication.getPrincipal();
            List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());
            for (GrantedAuthority authority : authorities
            ) {
                roles.add(authority.getAuthority());
            }
        }
        session.setAttribute("user",userDetails);
        model.addAttribute("bill_list",billPage);
        model.addAttribute("totalpages",totalPage);
        model.addAttribute("currentpage",pageIndex);
        return "./employee/billing";
    }
    @GetMapping("/employee/listmanufacturer")
    public String listmanu(Model model  ,@RequestParam("page")Optional<Integer>page,@RequestParam("size")Optional<Integer>size){
        int pageIndex = page.orElse(1);
        int pageSize =size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<Manufacturer> manufacturerPage = manufacturerDTOServices.getAllManufacturer(pageable);
        int totalPage = manufacturerPage.getTotalPages();
        model.addAttribute("list_manu",manufacturerPage);
        model.addAttribute("totalpages",totalPage);
        model.addAttribute("currentpage",pageIndex);
        return "./employee/listManufacturer";
    }
    @GetMapping("/employee/listcategory")
    public String listcate(Model model,@RequestParam("page")Optional<Integer>page,@RequestParam("size")Optional<Integer>size){
        int pageIndex = page.orElse(1);
        int pageSize =size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<Category> categoryPage = categoryDTOServices.getAllCategory(pageable);
        int totalPage = categoryPage.getTotalPages();
        model.addAttribute("list_cate",categoryPage);
        model.addAttribute("totalpages",totalPage);
        model.addAttribute("currentpage",pageIndex);
        return "./employee/listCategory";
    }
    @GetMapping("/employee/listproduct")
    public  String listproduct (Model model, HttpSession session   ,
                                @RequestParam("page") Optional<Integer>page,
                                @RequestParam("size")Optional<Integer>size){
        int pageIndex = page.orElse(1);
        int pageSize =size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<ProductDto> productDtoPage = productDTOServices.getAll(pageable);
        int totalPage = productDtoPage.getTotalPages();
        UserDetails userDetails= null;
        List<String> roles = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)){
            userDetails = (UserDetails) authentication.getPrincipal();
            List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());
            for (GrantedAuthority authority : authorities
                    ) {
                roles.add(authority.getAuthority());
            }
        }
        session.setAttribute("user",userDetails);
        model.addAttribute("list_product",productDtoPage);
        model.addAttribute("txt_search","");
        model.addAttribute("totalpages",totalPage);
        model.addAttribute("currentpage",pageIndex);
        return "./employee/listProduct";
    }
    @GetMapping("/employee/customerlist-search")
    public String userListSearch(HttpSession session,Model model  ,@RequestParam("page")Optional<Integer>page,@RequestParam("size")Optional<Integer>size,@RequestParam("txt_search") String name_search){
        int pageIndex = page.orElse(1);
        int pageSize =size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<User> userPage = null;
        if (name_search.equals("")) {
            userPage = userServiceImpl.findAllUser(pageable);
        } else {
            userPage = userServiceImpl.findByUserName(name_search, pageable);
        }
        int totalPage = userPage.getTotalPages();
        UserDetails userDetails= null;
        List<String> roles = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)){
            userDetails = (UserDetails) authentication.getPrincipal();
            List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());
            for (GrantedAuthority authority : authorities
            ) {
                roles.add(authority.getAuthority());
            }
        }
        List<Bill> billList = billService.getAllBills();
        session.setAttribute("user",userDetails);
        model.addAttribute("bill_list",billList);
        model.addAttribute("user_list",userPage);
        model.addAttribute("totalpages",totalPage);
        model.addAttribute("currentpage",pageIndex);
        model.addAttribute("txt_search",name_search);
        return "./employee/listCustomer";
    }
    @PostMapping("/employee/detailbill-search")
    public String detailBillSearch(HttpSession session,Model model  ,@RequestParam("page")Optional<Integer>page,@RequestParam("size")Optional<Integer>size
            ,@RequestParam("txt_search") String name_search){
        int pageIndex = page.orElse(1);
        int pageSize =size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<CartDto> detailBillPage = null;
        if (name_search.equals("")) {
            detailBillPage = detailDTOServices.getAllDetail(pageable);
        } else {
            detailBillPage = detailDTOServices.findByCode(name_search, pageable);
        }
        int totalPage = detailBillPage.getTotalPages();
        UserDetails userDetails= null;
        List<String> roles = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)){
            userDetails = (UserDetails) authentication.getPrincipal();
            List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());
            for (GrantedAuthority authority : authorities
            ) {
                roles.add(authority.getAuthority());
            }
        }
        session.setAttribute("user",userDetails);
        model.addAttribute("detailbill_list",detailBillPage);
        model.addAttribute("totalpages",totalPage);
        model.addAttribute("currentpage",pageIndex);
        model.addAttribute("txt_search",name_search);
        return "./employee/listFinishDetailBill";
    }
    @PostMapping("/employee/bill-search")
    public String billSearch(HttpSession session,Model model  ,@RequestParam("page")Optional<Integer>page,@RequestParam("size")Optional<Integer>size
            ,@RequestParam("txt_search") String name_search){
        int pageIndex = page.orElse(1);
        int pageSize =size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<BillDto> billPage = null;
        if (name_search.equals("")) {
            billPage = manufacturerDTOServices.getAllManufacturer(pageable);
        } else {
            billPage = billService.findByCode(name_search, pageable);
        }
        int totalPage = billPage.getTotalPages();
        UserDetails userDetails= null;
        List<String> roles = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)){
            userDetails = (UserDetails) authentication.getPrincipal();
            List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());
            for (GrantedAuthority authority : authorities
            ) {
                roles.add(authority.getAuthority());
            }
        }
        session.setAttribute("user",userDetails);
        model.addAttribute("bill_list",billPage);
        model.addAttribute("totalpages",totalPage);
        model.addAttribute("currentpage",pageIndex);
        model.addAttribute("txt_search",name_search);
        return "./employee/billing";
    }
    @PostMapping("/employee/listmanufacturer-search")
    public String listmanu(Model model  ,@RequestParam("page")Optional<Integer>page,@RequestParam("size")Optional<Integer>size,@RequestParam("txt_search") String name_search){
        int pageIndex = page.orElse(1);
        int pageSize =size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<ManufacturerDto> manufacturerPage = null;
        if (name_search.equals("")) {
            manufacturerPage = manufacturerDTOServices.getAllManufacturer(pageable);
        } else {
            manufacturerPage = manufacturerDTOServices.findByName(name_search, pageable);
        }
        int totalPage = manufacturerPage.getTotalPages();
        model.addAttribute("list_manu",manufacturerPage);
        model.addAttribute("totalpages",totalPage);
        model.addAttribute("currentpage",pageIndex);
        model.addAttribute("txt_search",name_search);
        return "./employee/listManufacturer";
    }
    @PostMapping("/employee/listcategory-search")
    public String listcateSeacrh(Model model,@RequestParam("page")Optional<Integer>page,@RequestParam("size")Optional<Integer>size,@RequestParam("txt_search") String name_search){
        int pageIndex = page.orElse(1);
        int pageSize =size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<CategoryDTO> categoryPage =null;
        if (name_search.equals("")) {
            categoryPage = categoryDTOServices.getAllCategory(pageable);
        } else {
            categoryPage = categoryDTOServices.findByName(name_search, pageable);
        }
        int totalPage = categoryPage.getTotalPages();
        model.addAttribute("txt_search",name_search);
        model.addAttribute("list_cate",categoryPage);
        model.addAttribute("totalpages",totalPage);
        model.addAttribute("currentpage",pageIndex);
        return "./employee/listCategory";
    }
    @PostMapping("/employee/listproduct-search")
    public  String listproductSearch (Model model, HttpSession session   ,
                                @RequestParam("page") Optional<Integer>page,
                                @RequestParam("size")Optional<Integer>size,@RequestParam("txt_search") String name_search){
        int pageIndex = page.orElse(1);
        int pageSize =size.orElse(5);
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<ProductDto> productDtoPage = null;
        if (name_search.equals("")) {
            productDtoPage = productDTOServices.getAll(pageable);
        } else {
            productDtoPage = productDTOServices.findByName(name_search, pageable);
        }
        int totalPage = productDtoPage.getTotalPages();
        UserDetails userDetails= null;
        List<String> roles = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)){
            userDetails = (UserDetails) authentication.getPrincipal();
            List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());
            for (GrantedAuthority authority : authorities
            ) {
                roles.add(authority.getAuthority());
            }
        }
        session.setAttribute("user",userDetails);
        model.addAttribute("list_product",productDtoPage);
        model.addAttribute("txt_search",name_search);
        model.addAttribute("totalpages",totalPage);
        model.addAttribute("currentpage",pageIndex);
        return "./employee/listProduct";
    }
//------------------------------ADD------------------------------
    @GetMapping("/employee/addmanufacturer")
    public String saveManu(Model model){
        model.addAttribute("manufacturer", new ManufacturerDto());
        return "./employee/addManufacturer";
    }
    @PostMapping("/employee/addmanufacturer")
    public String saveorUpdateManu(Model model,@ModelAttribute ManufacturerDto  manufacturerDto){
        manufacturerDTOServices.saveManufacturer(manufacturerDto);
        System.out.println("p");
        return "redirect:./listmanufacturer";
    }
    @GetMapping("/employee/addcategory")
    public String saveCate(Model model){
        model.addAttribute("category",new CategoryDTO());
        return "./employee/addCategory";
    }
    @PostMapping("/employee/addcategory")
    public String saveorUpdateCategory(Model model , @ModelAttribute CategoryDTO categoryDTO){
        categoryDTOServices.saveCategory(categoryDTO);
        return "redirect:./listcategory";
    }
    @GetMapping("/employee/addproduct")
    public String saveProduct(Model model){
        model.addAttribute("product",new ProductDto());
        Set<ManufacturerDto> manufacturerDtoList = manufacturerDTOServices.getAllManufacturer();
        Set<CategoryDTO> categoryDTOList = categoryDTOServices.getAllCategory();
        Set<Status> statusSet = statusDTOServices.getAllStatus();
        model.addAttribute("list_status",statusSet);
        model.addAttribute("list_manu",manufacturerDtoList);
        model.addAttribute("list_cate",categoryDTOList);
        System.out.println("p");
        return "./employee/addProduct";
    }

    @PostMapping("/employee/addproduct")
    public String saveorUpdateProduct(Model model, @ModelAttribute ProductDto productDto){
        try {
            MultipartFile multipartFile =productDto.getFile();
            String name_image = multipartFile.getOriginalFilename();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path_file+File.separator+name_image)));
            stream.write(multipartFile.getBytes());
            stream.close();
            productDto.setImage(name_image);
            productDTOServices.save(productDto);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "redirect:./listproduct";
    }
//------------------------------UPDATE------------------------------
@GetMapping("/employee/updateuser/{id}")
public String updateUser(HttpSession session,Model model,@ModelAttribute @PathVariable("id")long id){
    UserDetails userDetails= null;
    List<String> roles = new ArrayList<>();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)){
        userDetails = (UserDetails) authentication.getPrincipal();
        List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());
        for (GrantedAuthority authority : authorities
        ) {
            roles.add(authority.getAuthority());
        }
    }
    User user_view =  userServiceImpl.findByEmail(userDetails.getUsername());
    session.setAttribute("user",userDetails);
    session.setAttribute("user_view",user_view);
    User user2 = userRepository.findById(id).get();
    model.addAttribute("user2",user2);
    return "./employee/updateUser";
}
    @PostMapping("/employee/updateuser")
    public String updateUserr(Model model,@RequestParam("name")String name,@RequestParam("address")String address,@RequestParam("phone_number")int phone,@RequestParam("id")long id){
        try {
            User user2 = userRepository.findById(id).get();
            user2.setName(name);
            user2.setAddress(address);
            user2.setPhone_number(phone);
            model.addAttribute("user2",user2);
            userServiceImpl.saveUser2(user2);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "redirect:./dashboard";
    }
    @GetMapping("/employee/updatemanu/{id}")
    public String updateManufacturer(Model model,@ModelAttribute @PathVariable("id")int id){
        ManufacturerDto manufacturerDto = manufacturerDTOServices.findbyId(id).get(0);
        model.addAttribute("manufacturer",manufacturerDto);
        return "./employee/editManufacturer";
    }
    @PostMapping("/employee/updatemanu")
    public String updateManu(Model model,@ModelAttribute ManufacturerDto manufacturerDto){
        try {
            manufacturerDTOServices.updateManufacturer(manufacturerDto);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "redirect:./listmanufacturer" ;
    }
    @GetMapping("/employee/updatecate/{id}")
    public String updateCategory(Model model, @ModelAttribute @PathVariable("id")int id){
        CategoryDTO categoryDTO =  categoryDTOServices.findbyId(id).get(0);
        model.addAttribute("category",categoryDTO);
        return "./employee/editCategory";
    }
    @PostMapping("/employee/updatecate")
    public String updateCate(Model model , @ModelAttribute CategoryDTO categoryDTO){
        try {
            categoryDTOServices.updateCategory(categoryDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "redirect:./listcategory" ;
    }
    @GetMapping("/employee/update/{id}")
    public String updateProduct(Model model, @ModelAttribute @PathVariable("id")int id){
        ProductDto productDto = productDTOServices.findbyId(id).get(0);
        Set<ManufacturerDto> manufacturerDtoList = manufacturerDTOServices.getAllManufacturer();
        Set<Status> statusSet = statusDTOServices.getAllStatus();
        Set<CategoryDTO> categoryDTOList = categoryDTOServices.getAllCategory();
        model.addAttribute("product",productDto);
        model.addAttribute("list_status",statusSet);
        model.addAttribute("list_manu",manufacturerDtoList);
        model.addAttribute("list_cate",categoryDTOList);
        return "./employee/editProduct";
    }
    @PostMapping("/employee/update")
    public String update(Model model,@ModelAttribute ProductDto productDto,@RequestParam("id_product") int id){
        try {
            ProductDto productDto1 =  productDTOServices.findbyId(id).get(0);
            MultipartFile multipartFile = productDto.getFile();
            String name_image = multipartFile.getOriginalFilename();
            if (!StringUtils.isEmpty(name_image)) {
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path_file + File.separator + name_image)));
                stream.write(multipartFile.getBytes());
                stream.close();
                productDto.setImage(name_image);
            } else{
                productDto.setImage(productDto1.getImage());
            }
            productDTOServices.update(productDto);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "redirect:./listproduct";
    }
    //------------------------------DELETE------------------------------
    @GetMapping("/employee/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productDTOServices.delete(id);
        return "redirect:/employee/listproduct";
    }
    @GetMapping("/employee/deletecate/{id}")
    public String deleteCate(@PathVariable("id") int id ){
        categoryDTOServices.deleteCategory(id);
        return "redirect:/employee/listcategory";
    }
    @GetMapping("/employee/delete-bill/{id}")
    public String deleteBill(@PathVariable("id") int id ){
        billService.deleteBill(id);
        return "redirect:/employee/bill";
    }
    @GetMapping("/employee/delete-user/{id}")
    public String deleteUser(@PathVariable("id") Long id ){
        userServiceImpl.delete(id);
        return "redirect:/employee/customerlist";
    }
    @GetMapping("/employee/delete-detailbill/{id}")
    public String deleteDetailBill(@PathVariable("id") long id ){

        try {
            detailDTOServices.deleteDetail(id);
            return "redirect:/employee/detailbill";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "redirect:/employee/detailbill";
        }
    }
    @GetMapping("/employee/deletemanu/{id}")
    @Transactional
    public  String deleteManu(@PathVariable("id") int id ){
        manufacturerDTOServices.deleteManufacturer(id);
        return "redirect:/employee/listmanufacturer";
    }

}
