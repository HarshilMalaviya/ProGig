package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Dto.UserDto;
import com.Ntra.ProGig.Entity.UserRole;
import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Exception.NoContentException;
import com.Ntra.ProGig.Exception.UserNotFoundException;
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
public class ClientService {
    @Autowired
    private UserRepo repo;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDto> getAllClients(){
        try {
            List<User> users =this.repo.findAllByRole(UserRole.CLIENT);
            List<UserDto> userDtos = users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
            return userDtos;
        }
        catch (NoContentException e){
            throw  new NoContentException("User is not available");
        }

    }

    public UserDto getClientByUsername(String username){
        try {
            User users = this.repo.findByUsernameAndRole(username, UserRole.CLIENT);
            UserDto userDtos = this.UserToDto(users);
            return userDtos;
        }
        catch (UserNotFoundException ex){
            throw new UserNotFoundException("User Not There");
        }
    }

    public UserDto getClientByEmail(String email){
        try {
            User users =this.repo.findByEmailAndRole(email,UserRole.CLIENT);
            UserDto userDtos = this.UserToDto(users);
            return userDtos;
        }
        catch (UserNotFoundException e){
            throw new UserNotFoundException("there is no user with such Email!!");
        }

    }

    public Integer clientCount(){
        List<User> users =this.repo.findAllByRole(UserRole.CLIENT);
        List<UserDto> userDtos = users.stream().map(this::UserToDto).toList();
        return userDtos.size();
    }

    public UserDto getClientById(Integer id){
        try {
            User users =this.repo.findByIdAndRole(id,UserRole.CLIENT);
            UserDto userDtos = this.UserToDto(users);
            return userDtos;
        }
        catch (UsernameNotFoundException e){
            throw new UserNotFoundException("there is no user related to the Id given !!");
        }

    }

    public String deleteClient(Integer id){
        try {
            repo.deleteById(id);
            return "Id Deleted"+id;
        }
        catch (UsernameNotFoundException e){
            throw new UserNotFoundException("there is no such data");
        }

    }

    public User acceptClient(Integer id){
        Optional<User> client= this.repo.findById(id);
        if (client.isPresent()){
            User user = client.get();
            UserDto userDto = this.UserToDto(user);
            userDto.setStatus("ACCEPTED");
            return this.DtoToUser(userDto);
        }
        return null;
    }

    public User rejectClient(Integer id,String description){
        Optional<User> client = this.repo.findById(id);
        if (client.isPresent()){
            User user = client.get();
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

