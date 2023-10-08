package com.example.fullproject.services;

import com.example.fullproject.dto.BillDto;
import com.example.fullproject.entities.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BillServices <T>{
    Page<T> getAllBills(Pageable pageable);
    List getAllBills();

    Boolean saveBill(BillDto billDto);
    Bill saveBill1(Bill bill);
    Boolean saveAllBill(List<BillDto> billDtoList);
    Boolean updateBill(BillDto billDto);

    Boolean deleteBill(int t);
}
