package com.example.fullproject.mapper;

import com.example.fullproject.dto.BillDto;
import com.example.fullproject.dto.CategoryDTO;
import com.example.fullproject.entities.Bill;
import com.example.fullproject.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class BillMapper {
    public BillDto todtoBill(Bill bill) {
        return BillDto.builder().
                id(bill.getId()).
                date_created(bill.getDate_created()).
                delivery(bill.getDelivery()).
                user(bill.getUser()).
                detailBills(bill.getDetailBills()).
                code(bill.getCode()).
                build();
    }
}
