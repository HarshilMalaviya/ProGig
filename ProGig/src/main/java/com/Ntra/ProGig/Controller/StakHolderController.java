package com.Ntra.ProGig.Controller;


import com.Ntra.ProGig.Dto.StackHolder2Dto;
import com.Ntra.ProGig.Entity.StakHolder;
import com.Ntra.ProGig.Service.StakHolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/StackHolder")


public class StakHolderController {
    private final StakHolderService userService;
    @GetMapping("/Users")
    public List<StackHolder2Dto> getAllUsers()
    {   List<StackHolder2Dto> list = userService.getusers();
//        if(list.size()<=0){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        else{
//         return ResponseEntity.of(Optional.of(list));
//        }
        return list;
    }
    @GetMapping("/Count")
    public int Count(){
        return userService.StackHolderCount();
    }
    @GetMapping("/users/{username}")
    public StackHolder2Dto findbyUserid(@PathVariable String username){
        StackHolder2Dto user=userService.getuserbyusername(username);
//        if(user==null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        else {
//            return ResponseEntity.of(Optional.of(user));
//        }
        return user;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<StakHolder> EditeUser(@RequestBody StackHolder2Dto user1, @PathVariable int id) {
        StakHolder user= userService.EditeUser(user1);
         return ResponseEntity.ok(user);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
         userService.deletebyid(id);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
