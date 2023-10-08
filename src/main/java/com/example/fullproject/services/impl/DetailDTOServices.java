package com.example.fullproject.services.impl;

import com.example.fullproject.dto.BillDto;
import com.example.fullproject.dto.CartDto;
import com.example.fullproject.entities.DetailBill;
import com.example.fullproject.mapper.DetailBillMapper;
import com.example.fullproject.repository.DetailBillRepository;
import com.example.fullproject.services.DetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetailDTOServices implements DetailServices {
    @Autowired
    DetailBillRepository detailBillRepository;
    @Autowired
    DetailBillMapper detailBillMapper;
    @Override
    public Page getAllDetail(Pageable pageable) {
        return detailBillRepository.findAll(pageable).map(x->detailBillMapper.todtoCart(x));
    }

    @Override
    public List getAllDetail() {
        return detailBillRepository.findAll().stream().map(x -> detailBillMapper.todtoCart(x)).collect(Collectors.toList());
    }

    @Override
    public Boolean saveDetail(CartDto detailBillDTO) {
        try {

            detailBillRepository.save(new DetailBill().toEntityDetailBill(detailBillDTO));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public DetailBill saveDetail(DetailBill detailBill) {
        try {
            return detailBillRepository.save(detailBill);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional(timeout = 10)
    public Boolean saveAllDetail(List list) {
        try {
            detailBillRepository.saveAll(list);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updateDetail(CartDto detailBillDTO) {
        return null;
    }


    @Override
    public Boolean deleteDetail(long t) {
        DetailBill detailBill = detailBillRepository.findById(t).get();
        detailBillRepository.delete(detailBill);
        System.out.println("a");
        return true;
    }
    public Page<CartDto> findByCode(String code, Pageable pageable) {
        return detailBillRepository.findByBillCode(code, pageable).map(x -> detailBillMapper.todtoCart(x));
    }

}
