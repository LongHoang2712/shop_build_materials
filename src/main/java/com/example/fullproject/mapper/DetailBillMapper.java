package com.example.fullproject.mapper;

import com.example.fullproject.dto.CartDto;
import com.example.fullproject.dto.ProductDto;
import com.example.fullproject.entities.DetailBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetailBillMapper {
@Autowired
ProductMapper mapper;
    public CartDto todtoCart(DetailBill detailBill) {
        return CartDto.builder().
                id(detailBill.getId()).
                quantity(detailBill.getQuantity()).
                product(mapper.todto(detailBill.getProduct())).
                bill(detailBill.getBill()).
                build();
    }

}
