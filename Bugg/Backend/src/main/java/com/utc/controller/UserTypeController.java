package com.utc.controller;

import com.utc.dto.UserTypeDTO;
import com.utc.entity.Guests;
import com.utc.entity.UserType;
import com.utc.service.IUserTypeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@RestController
@RequestMapping("/UTCDemo/userType")
public class UserTypeController {

    @Autowired
    private IUserTypeService userTypeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> listUserType(){
        List<UserType> lst = userTypeService.getAllUseType();
        List<UserTypeDTO> dtoList = modelMapper.map(lst,new TypeToken<List<UserTypeDTO>>(){}.getType());
        return new ResponseEntity<List<UserTypeDTO>>(dtoList, HttpStatus.OK);
    }
}
