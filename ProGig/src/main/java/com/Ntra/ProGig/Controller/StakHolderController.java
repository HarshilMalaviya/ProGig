package com.Ntra.ProGig.Controller;

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

public class StakHolderController {
    private final StakHolderService userService;
    @GetMapping("/Users")
    public ResponseEntity<List<StakHolder>> getAllUsers()
    {   List<StakHolder> list = userService.getusers();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
         return ResponseEntity.of(Optional.of(list));
        }
    }
    @GetMapping("/users/{id}")
    public StakHolder findbyUserid(@PathVariable int id) {
        StakHolder user=userService.getuserbyid(id);
//        if(user==null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        else {
//            return ResponseEntity.of(Optional.of(user));
//        }
        return user;
    }
    @PutMapping("update")
    public ResponseEntity<StakHolder> EditeUser(@RequestBody StakHolder user1){
        StakHolder user= userService.EditeUser(user1);
         return ResponseEntity.of(Optional.of(user));
    }
    @DeleteMapping("/delet/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
         userService.deletebyid(id);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
