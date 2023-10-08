package com.example.fullproject.dto;

import com.example.fullproject.entities.Delivery;
import com.example.fullproject.entities.DetailBill;
import com.example.fullproject.entities.User;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private int id;
    private Date date_created;
    private Delivery delivery ;

    private User user;
    private List<DetailBill> detailBills;
    private String code;
}
