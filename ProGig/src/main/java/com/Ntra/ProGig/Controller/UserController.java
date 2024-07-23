package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;
    @GetMapping("/Users")
    public ResponseEntity<List<User>> getAllUsers()
    {   List<User> list = userService.getusers();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
         return ResponseEntity.of(Optional.of(list));
        }
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> findbyUserid(@PathVariable int id) {
        User user=userService.getuserbyid(id);
        if(user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(user));
        }
    }
    @PutMapping("update")
    public ResponseEntity<User> EditeUser(@RequestBody User user1){
        User user= userService.EditeUser(user1);
         return ResponseEntity.of(Optional.of(user));
    }
    @DeleteMapping("/delet/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
         userService.deletebyid(id);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
