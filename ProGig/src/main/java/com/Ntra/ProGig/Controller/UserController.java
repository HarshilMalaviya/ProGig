package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Dto.JobDto;
import com.Ntra.ProGig.Dto.UserDto;
import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchJobs(@RequestParam String keyword) {
        List<UserDto> userDtos=service.searchJobs(keyword);
        return ResponseEntity.ok(userDtos);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserDto user,@PathVariable int id) {
        User updatedUser = service.updateUser(user,id);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
