package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Entity.ApiResponse;
import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Service.FreelancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/freelancer")
public class FreelancerController {

    @Autowired
    private FreelancerService service;

    @GetMapping("/getAllFreelancer")
    public ResponseEntity<List<User>> getAllFreelancer(){
        return ResponseEntity.ok(this.service.getAllFreelancer());
    }

    @GetMapping("/getFreelancerByUsername/{username}")
    public ResponseEntity<Optional<User>> getFreelancerByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(this.service.getFreelancerByUsername(username));
    }

    @GetMapping("/getFreelancerByEmail/{email}")
    public ResponseEntity<Optional<User>> getFreelancerByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(this.service.getFreelancerByEmail(email));
    }

    @GetMapping("/getFreelancerById/{id}")
    public ResponseEntity<Optional<User>> getFreelancerById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.service.getFreelancerById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFreelancer(@PathVariable("id") Integer id){
        service.deleteFreelancer(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse
                ("user deleted successfully",true), HttpStatus.OK);
    }

}
