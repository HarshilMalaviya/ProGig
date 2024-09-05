package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.UserRole;
import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Dto.UserDto;
import com.Ntra.ProGig.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<User> users =this.repo.findAllByRole(UserRole.FREELANCER);
        List<UserDto> userDtos = users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
        return userDtos;
    }


    public List<UserDto> getFreelancerByUsername(String username){
        Optional<User> users =this.repo.findByUsernameAndRole(username,UserRole.FREELANCER);
        List<UserDto> userDtos = users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    public List<UserDto> getFreelancerByEmail(String email){
        Optional<User> users =this.repo.findByEmailAndRole(email,UserRole.FREELANCER);
        List<UserDto> userDtos = users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    public List<UserDto> getFreelancerById(Integer id){
        Optional<User> users =this.repo.findByIdAndRole(id,UserRole.FREELANCER);
        List<UserDto> userDtos = users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    public void deleteFreelancer(Integer id){
        repo.deleteById(id);
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
