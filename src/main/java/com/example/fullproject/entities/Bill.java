package com.example.fullproject.entities;




import com.example.fullproject.dto.BillDto;
import com.example.fullproject.dto.CategoryDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private Date date_created;
    @ManyToOne()
    @JoinColumn(name = "delivery_id")
    private Delivery delivery ;

    @ManyToOne()
    @JoinColumn(name = "id_user")
    private User user;
    private String code;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bill",cascade = CascadeType.MERGE)
    private List<DetailBill> detailBills;

    public Bill(Date date_created, Delivery delivery, User user, List<DetailBill> detailBills, String code) {
        this.date_created = date_created;
        this.delivery = delivery;
        this.user = user;
        this.detailBills = detailBills;
        this.code = code;
    }



    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", date_created=" + date_created +
                ", delivery=" + delivery +
                ", user=" + user +
                ", code='" + code + '\'' +
                '}';
    }

    public Bill(int id, Date date_created, Delivery delivery, User user, String code) {
        this.id = id;
        this.date_created = date_created;
        this.delivery = delivery;
        this.user = user;
        this.code = code;
    }

    public Bill toEntityBill(BillDto billDto) {
        return Bill.builder().
                id(billDto.getId()).
                date_created(billDto.getDate_created()).
                delivery(billDto.getDelivery()).
                user(billDto.getUser()).
                detailBills(billDto.getDetailBills())
                .code(billDto.getCode())
                .build();
    }

}
