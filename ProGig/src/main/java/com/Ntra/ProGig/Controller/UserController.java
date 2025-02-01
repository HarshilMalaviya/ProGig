package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Dto.UserDto;
import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://192.168.0.168:5173/**")
@RestController
@RequestMapping("/user_api")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(this.service.getAllUser());
    }
    @PostMapping("/accept/{username}")
    public ResponseEntity<User> acceptFreelancer(@PathVariable String username){
        User acceptedFreelancer = service.acceptUser(username);
        return acceptedFreelancer != null ? ResponseEntity.ok(acceptedFreelancer) : ResponseEntity.notFound().build();
    }

    @PostMapping("/reject/{username}")
    public ResponseEntity<User> rejectFreelancer(@PathVariable String username, @RequestBody String description) throws JsonProcessingException {
        User rejectedFreelancer = service.rejectUser(username, description);
        return rejectedFreelancer != null ? ResponseEntity.ok(rejectedFreelancer) : ResponseEntity.notFound().build();
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<UserDto> getFreelancerByUsername(@PathVariable String username){
        return ResponseEntity.ok(this.service.getUserByUsername(username));
    }

}
