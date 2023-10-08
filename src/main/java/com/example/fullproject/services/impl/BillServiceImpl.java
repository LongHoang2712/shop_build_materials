package com.example.fullproject.services.impl;

import com.example.fullproject.dto.BillDto;
import com.example.fullproject.dto.ProductDto;
import com.example.fullproject.entities.Bill;
import com.example.fullproject.entities.DetailBill;
import com.example.fullproject.mapper.BillMapper;
import com.example.fullproject.repository.BillRepository;
import com.example.fullproject.services.BillServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillServices {
    @Autowired
    BillRepository billRepository;
    @Autowired
    BillMapper billMapper;

    @Override
    public Page getAllBills(Pageable pageable) {
        return billRepository.findAll(pageable).map(x -> billMapper.todtoBill(x));
    }

    @Override
    public List getAllBills() {
        return billRepository.findAll().stream().map(x -> billMapper.todtoBill(x)).collect(Collectors.toList());
    }

    @Override
    public Boolean saveBill(BillDto billDto) {
        try {
            billRepository.save(new Bill().toEntityBill(billDto));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Bill saveBill1(Bill bill) {
        try {

            return  billRepository.save(new Bill().toEntityBill(billMapper.todtoBill(bill)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public Boolean saveAllBill(List list) {
        try {
            billRepository.saveAll(list);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updateBill(BillDto billDto) {
        try {
            billRepository.save(new Bill().toEntityBill(billDto));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteBill(int t) {
        try {
            Bill billdto = billRepository.findById(t).get();
            billRepository.delete(billdto);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }



    }

    public List<BillDto> findbyId(int id) {
        return billRepository.findById(id).stream().map(x -> billMapper.todtoBill(x)).toList();
    }
    public Page<BillDto> findByCode(String code, Pageable pageable) {
        return billRepository.findByCode(code, pageable).map(x -> billMapper.todtoBill(x));
    }

}
