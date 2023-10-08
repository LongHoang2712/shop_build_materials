package com.example.fullproject.security;

import com.example.fullproject.entities.Role;
import com.example.fullproject.entities.User;
import com.example.fullproject.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomerUserDetailService implements UserDetailsService {
    @Autowired
    private UserServices userServices;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userServices.findByEmail(username);
        if (user != null){
            return
                    new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),
                            mapRolesToAuthorities(user.getRole()));
        }
        return null;
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities (Collection<Role> roles){
        Collection<? extends GrantedAuthority > mapRole = roles.stream().
                map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return mapRole;
    }
}
