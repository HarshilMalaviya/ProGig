package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Dto.StackHolder2Dto;
import com.Ntra.ProGig.Entity.StakHolder;
import com.Ntra.ProGig.Exception.OkStatus;
import com.Ntra.ProGig.Exception.UserNotFoundException;
import com.Ntra.ProGig.Repository.StakHolderRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StakHolderService {

  @Autowired
    private final StakHolderRepo userRepo;

  @Autowired
  private ModelMapper modelMapper;

  public List<StackHolder2Dto> getusers()
  {
    try {
       List<StakHolder> stakHolder = this.userRepo.findAll();
       return stakHolder.stream().map(this::EntityToStackeDto).toList();
    }catch (UsernameNotFoundException e){
      throw new UsernameNotFoundException("There is no StackHolder");
    }
  }
  public StackHolder2Dto getuserbyid(int id)
  {
    StakHolder stakHolder = this.userRepo.findById(id).orElseThrow(()->new UserNotFoundException("THERE IS NO USER ID :"+id));
    return this.EntityToStackeDto(stakHolder);
  }
  public Void deletebyid(int id){
    userRepo.deleteById(id);
    throw  new OkStatus("StackHolder Is deleted");
  }
  public StakHolder EditeUser (StackHolder2Dto stackHolderDTO){

    StakHolder user=userRepo.findById(stackHolderDTO.getId()).orElseThrow(()->new UserNotFoundException("User not Present"));
    StackHolder2Dto exsistingUser = new StackHolder2Dto();
    exsistingUser.setLastname(stackHolderDTO.getLastname());
    exsistingUser.setFirstname(stackHolderDTO.getFirstname());
    exsistingUser.setUsername(stackHolderDTO.getUsername());
    exsistingUser.setEmail(stackHolderDTO.getEmail());
    exsistingUser.setRole(stackHolderDTO.getRole());
    exsistingUser.setContact(stackHolderDTO.getContact());
    return this.StackDTOtoEntity(exsistingUser);

  }

  public StackHolder2Dto EntityToStackeDto(StakHolder stakHolder){
    StackHolder2Dto stackHolderDTO= new StackHolder2Dto();
    stackHolderDTO=modelMapper.map(stakHolder,StackHolder2Dto.class);
    return stackHolderDTO;
  }
  public StakHolder StackDTOtoEntity(StackHolder2Dto stackHolderDTO){
    StakHolder stakHolder=new StakHolder();
    stakHolder=modelMapper.map(stackHolderDTO,StakHolder.class);
    return stakHolder;
  }
}
