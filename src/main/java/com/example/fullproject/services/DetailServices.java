package com.example.fullproject.services;

import com.example.fullproject.dto.CartDto;
import com.example.fullproject.entities.DetailBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DetailServices <T>{
    Page<T> getAllDetail(Pageable pageable);
    List getAllDetail();

    Boolean saveDetail(CartDto detailBillDTO);
    DetailBill saveDetail(DetailBill detailBill);
    Boolean saveAllDetail(List<CartDto> cartDtoList);

    Boolean updateDetail(CartDto detailBillDTO);

    Boolean deleteDetail(long t);
}
