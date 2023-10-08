package com.example.fullproject.services.impl;

import com.example.fullproject.dto.UserDto;
import com.example.fullproject.entities.Category;
import com.example.fullproject.entities.Role;
import com.example.fullproject.entities.User;
import com.example.fullproject.repository.RolesRepository;
import com.example.fullproject.repository.UserRepository;
import com.example.fullproject.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class UserServiceImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;






    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public Page<User> findByUserName(String username, Pageable pageable) {
        return userRepository.findUserByName(username,pageable);
    }

    @Override
    public void saveUser(UserDto userDto) {
        String password_hash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(password_hash);
        User user = new User().toEntites(userDto);
        Role role = rolesRepository.findByName("USER");
        if (role == null) {
            role = checkExist();
        }
        user.setRole(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public boolean saveUser2(User user) {
        try {
            userRepository.save(user);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    private Role checkExist() {
        Role role = new Role();
        role.setName("USER");
        return rolesRepository.save(role);
    }

    @Override
    public Page<User> findAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Boolean delete(Long t) {
        User user1 = userRepository.findById(t).get();
        userRepository.delete(user1);
        return true;
    }
}
