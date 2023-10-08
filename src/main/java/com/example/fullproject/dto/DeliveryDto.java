package com.example.fullproject.dto;

import com.example.fullproject.entities.Bill;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {
    private  int id;
    private String name;
    private List<Bill> bill ;
}
