package com.example.fullproject.services;

import com.example.fullproject.dto.UserDto;
import com.example.fullproject.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserServices {

    User findByEmail(String email);

    void saveUser(UserDto userDto);
    boolean saveUser2(User user);

    Page<User> findAllUser(Pageable pageable);
    Boolean delete(Long t);
}
