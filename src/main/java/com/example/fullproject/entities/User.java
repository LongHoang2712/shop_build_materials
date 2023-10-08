package com.example.fullproject.entities;

import com.example.fullproject.dto.UserDto;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.repository.cdi.Eager;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String password;
    private String address;
    private int phone_number;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns ={@JoinColumn(name = "USER_ID",referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID",referencedColumnName = "ID")}
    )
    private List<Role> role= new ArrayList<>();

   @OneToMany( mappedBy = "user" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Bill> bills = new ArrayList<>();

    public User( String name, String address, int phone_number) {

        this.name = name;
        this.address = address;
        this.phone_number = phone_number;

    }

    public User toEntites(UserDto userDto){
        return User.builder().
                id(userDto.getId()).
                name(userDto.getName()).
                email(userDto.getEmail()).
                password(userDto.getPassword()).
                address(userDto.getAddress()).phone_number(userDto.getPhone_number()).
                build();
    }
}
