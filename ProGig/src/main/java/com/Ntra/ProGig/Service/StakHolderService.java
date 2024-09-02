package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.StakHolder;
import com.Ntra.ProGig.Repository.StakHolderRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StakHolderService {

  @Autowired
    private final StakHolderRepo userRepo;
  public List<StakHolder> getusers()
  {
    return userRepo.findAll();
  }
  public StakHolder getuserbyid(int id)
  {
    return userRepo.findById(id).orElse(null);
  }
  public String deletebyid(int id){
    userRepo.deleteById(id);
    return"user deleted"+id;
  }
  public StakHolder EditeUser (StakHolder user){
    StakHolder exsistingUser=userRepo.findById(user.getId()).orElse(null);
    exsistingUser.setLastname(user.getLastname());
    exsistingUser.setFirstname(user.getFirstname());
    exsistingUser.setUsername(user.getUsername());
    exsistingUser.setEmail(user.getEmail());
    exsistingUser.setRole(user.getRole());
    exsistingUser.setContact(user.getContact());
    return userRepo.save(exsistingUser);
  }

}
