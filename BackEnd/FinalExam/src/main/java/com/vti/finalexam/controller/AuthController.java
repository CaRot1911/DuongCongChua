package com.vti.finalexam.controller;


import com.vti.finalexam.dto.JwtResponseDTO;
import com.vti.finalexam.dto.SignInDTO;
import com.vti.finalexam.dto.SignUpDTO;
import com.vti.finalexam.entiy.Account;
import com.vti.finalexam.service.IAccountService;
import com.vti.finalexam.untils.JWTUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@Validated
public class AuthController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JWTUtil jwtUtils;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInDTO signInDTO){
        System.out.println(signInDTO.toString());
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(signInDTO.getUserName(),signInDTO.getPassWord())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponseDTO(
           jwtToken, userDetails.getUsername(),userDetails.getAuthorities().toString()
        ));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDTO signUpDTO){

        Account account = modelMapper.map(signUpDTO,Account.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String eCryptPassword = bCryptPasswordEncoder.encode(account.getPassWord());
        account.setPassWord(eCryptPassword);
        accountService.createAccountSignUp(account);
        return ResponseEntity.ok().body("Register account successfully");
    }
}
