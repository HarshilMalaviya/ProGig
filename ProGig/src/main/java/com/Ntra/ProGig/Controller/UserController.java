package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Dto.UserDto;
import com.Ntra.ProGig.Service.FreelancerService;
import com.Ntra.ProGig.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(this.service.getAllUser());
    }

}
