package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Entity.ApiResponse;
import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Dto.UserDto;
import com.Ntra.ProGig.Service.FreelancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/freelancer")

public class FreelancerController {

    @Autowired
    private FreelancerService service;

    @GetMapping("/getAllFreelancer")
    public List<UserDto> getAllFreelancer(){
        return service.getAllFreelancer();
    }

    @GetMapping("/getFreelancerByUsername/{username}")
    public ResponseEntity<UserDto> getFreelancerByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(this.service.getFreelancerByUsername(username));
    }

    @GetMapping("/getFreelancerByEmail/{email}")
    public ResponseEntity<UserDto> getFreelancerByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(this.service.getFreelancerByEmail(email));
    }

    @GetMapping("/getFreelancerById/{id}")
    public ResponseEntity<UserDto> getFreelancerById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.service.getFreelancerById(id));
    }


    @GetMapping("/freelancerCount")

    public ResponseEntity<Integer> freelancerCount(){
        return ResponseEntity.ok(service.freelancerCount());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFreelancer(@PathVariable("id") Integer id){
        service.deleteFreelancer(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse
                ("user deleted successfully",true), HttpStatus.OK);
    }







}
