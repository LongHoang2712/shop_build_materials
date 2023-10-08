package com.example.fullproject.services.impl;

import com.example.fullproject.mapper.StatusMapper;
import com.example.fullproject.repository.StatusRepository;
import com.example.fullproject.services.StatusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatusDTOServices implements StatusServices {
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    StatusMapper mapperStatus;

    @Override
    public Set getAllStatus() {
        return statusRepository.findAll().stream().map(x -> mapperStatus.todtoStatus(x)).collect(Collectors.toSet());
    }
}
