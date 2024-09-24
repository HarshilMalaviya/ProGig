package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.UserRole;
import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Dto.UserDto;
import com.Ntra.ProGig.Exception.NoContentException;
import com.Ntra.ProGig.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FreelancerService {
    @Autowired
    private UserRepo repo;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDto> getAllFreelancer(){
        try {
            List<User> users =this.repo.findAllByRole(UserRole.FREELANCER);
            List<UserDto> userDtos = users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
            return userDtos;
        }catch (NoContentException e){
            throw new NoContentException("No_Content");
        }
    }

    public Integer freelancerCount(){
        List<User> users =this.repo.findAllByRole(UserRole.FREELANCER);
        List<UserDto> userDtos = users.stream().map(this::UserToDto).toList();
        return userDtos.size();
    }


    public UserDto getFreelancerByUsername(String username){
        try {
            User users =this.repo.findByUsernameAndRole(username,UserRole.FREELANCER);
            UserDto userDtos = this.UserToDto(users);
            return userDtos;
        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("There is No such User");
        }

    }

    public UserDto getFreelancerByEmail(String email){
        try {
            User users =this.repo.findByEmailAndRole(email,UserRole.FREELANCER);
            UserDto userDtos = this.UserToDto(users);
            return userDtos;
        }catch (UsernameNotFoundException){
            throw new UsernameNotFoundException("there is no user with such Email!!");
        }

    }

    public UserDto getFreelancerById(Integer id){
        try {
            User users =this.repo.findByIdAndRole(id,UserRole.FREELANCER);
            UserDto userDtos = this.UserToDto(users);
            return userDtos;
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("ID_Not_Found");
        }
    }

    public String deleteFreelancer(Integer id){
        try {
            repo.deleteById(id);
            return "ID deleted"+id;
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("ID_Not_Found");
        }
    }

    public User acceptFreelancer(Integer id){
        Optional<User> freelancer= this.repo.findById(id);
        if (freelancer.isPresent()){
            User user = freelancer.get();
            UserDto userDto = this.UserToDto(user);
            userDto.setStatus("ACCEPTED");
            return this.DtoToUser(userDto);
        }
        return null;
    }

    public User rejectFreelancer(Integer id,String description){
        Optional<User> freelancer= this.repo.findById(id);
        if (freelancer.isPresent()){
            User user = freelancer.get();
            UserDto userDto = this.UserToDto(user);
            userDto.setStatus("REJECTED");
            userDto.setWhyRejected(description);
            return this.DtoToUser(userDto);
        }
        return null;
    }

    private UserDto UserToDto(User user){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        UserDto userDto = new UserDto();
        userDto = new ModelMapper().map(user,UserDto.class);
        return userDto;
    }

    private User DtoToUser(UserDto userDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        User user = new User();
        user = new ModelMapper().map(userDto,User.class);
        return user;
    }

}
