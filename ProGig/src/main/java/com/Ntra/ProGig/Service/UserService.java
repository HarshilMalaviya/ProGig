package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Dto.UserDto;
import com.Ntra.ProGig.Entity.Skills;
import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Entity.UserRole;
import com.Ntra.ProGig.Exception.NoContentException;
import com.Ntra.ProGig.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDto> getAllUser(){
        try {
            List<User> users = this.repo.findAll();

            return users.stream().map(this::UserToDto).toList();
        }
        catch (NoContentException e){
            throw  new NoContentException("Data is not present");
        }
    }

    public User saveUser(User user) {
        User createUser = this.repo.save(user);
        createUser.setId(user.getId());
        createUser.setFirstName(user.getFirstName());
        createUser.setLastName(user.getLastName());
        createUser.setEmail(user.getEmail());
        createUser.setUsername(user.getUsername());
        createUser.setPassword(user.getPassword());
        createUser.setDescription(user.getDescription());
        createUser.setDescription(user.getDescription());
        return createUser;
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
