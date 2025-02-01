package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Dto.UserDto;
import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Exception.NoContentException;
import com.Ntra.ProGig.Repository.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;
    @Autowired
    private ModelMapper modelMapper;

    public List<UserDto> getAllUser(){
        try {
            List<User> users =this.repo.findAll();
            List<UserDto> userDtos = users.stream().map(this::UserToDto).collect(Collectors.toList());
            return userDtos;
        }catch (NoContentException e){
            throw new NoContentException("No_Content");
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

    public User acceptUser(String username){
        Optional<User> freelancer= this.repo.findByUsername(username);
        if (freelancer.isPresent()){
            User user = freelancer.get();
            UserDto userDto = this.UserToDto(user);
            userDto.setStatus("ACCEPTED✅");
            userDto.setWhyRejected(null);
            return this.repo.save(DtoToUser(userDto));
        }

        return null;
    }

    public User rejectUser(String username,String description) throws JsonProcessingException {
        Optional<User> freelancer= this.repo.findByUsername(username);
        if (freelancer.isPresent()){
            User user = freelancer.get();
            UserDto userDto = this.UserToDto(user);
            userDto.setStatus("REJECTED❌");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(description);
            userDto.setWhyRejected(jsonNode.get("rejectionReason").asText());
            return repo.save(DtoToUser(userDto));
        }
        return null;
    }

    public UserDto getUserByUsername(String username){

        try {
            Optional<User> users =this.repo.findByUsername(username);
            UserDto userDtos = this.UserToDto(users.orElse(null));
            return userDtos;
        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("There is No such User");
        }

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
