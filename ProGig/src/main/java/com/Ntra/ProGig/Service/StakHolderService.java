package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.StakHolder;
import com.Ntra.ProGig.Exception.UserNotFoundException;
import com.Ntra.ProGig.Repository.StakHolderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StakHolderService {

  @Autowired
    private final StakHolderRepo userRepo;
  public List<StakHolder> getusers()
  {
    try {
      return userRepo.findAll();
    }catch (UsernameNotFoundException e){
      throw new UsernameNotFoundException("There is no StackHolder");
    }

  }
  public StakHolder getuserbyid(int id)
  {
    return userRepo.findById(id).orElseThrow(()->new UserNotFoundException("THERE IS NO USER ID :"+id));
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
