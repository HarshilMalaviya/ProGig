package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
  @Autowired
    private final UserRepo userRepo;
  public List<User> getusers()
  {
    return userRepo.findAll();
  }
  public User getuserbyid(int id)
  {
    return userRepo.findById(id).orElse(null);
  }
  public String deletebyid(int id){
    userRepo.deleteById(id);
    return"user deleted"+id;

  }
  public User EditeUser (User user){
    User exsistingUser=userRepo.findById(user.getId()).orElse(null);
    exsistingUser.setLastname(user.getLastname());
    exsistingUser.setFirstname(user.getFirstname());
    exsistingUser.setUsername(user.getUsername());
    exsistingUser.setEmail(user.getEmail());
    exsistingUser.setRole(user.getRole());
    exsistingUser.setContact(user.getContact());
    return userRepo.save(exsistingUser);
  }

}
