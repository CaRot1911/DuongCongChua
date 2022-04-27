package com.utc.service;

import com.utc.entity.UserServices;
import com.utc.entity.UserType;
import com.utc.repository.IUserServicesRepository;
import com.utc.repository.IUserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserTypeService implements IUserTypeService{

    @Autowired
    private IUserTypeRepository userTypeRepository;

    @Override
    public List<UserType> getAllUseType() {
        return userTypeRepository.findAll();
    }
}
