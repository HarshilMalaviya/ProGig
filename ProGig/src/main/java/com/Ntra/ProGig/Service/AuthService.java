package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Dto.LoginDTO;
import com.Ntra.ProGig.Dto.StackHolderDTO;
import com.Ntra.ProGig.Entity.AuthenticationResponse;
import com.Ntra.ProGig.Entity.StakHolder;
import com.Ntra.ProGig.Exception.UserAlreadyExistsException;
import com.Ntra.ProGig.Exception.UserNotFoundException;
import com.Ntra.ProGig.Repository.StakHolderRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

;import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final StakHolderRepo userRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private ModelMapper modelMapper;

    public AuthenticationResponse authenticate(LoginDTO request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        StakHolder user=userRepo.findByUsername(request.getUsername()).orElseThrow(()->new UserNotFoundException("user is not there")) ;

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse register(StackHolderDTO request)  {
        Optional<StakHolder> existingUser = userRepo.findByUsername(request.getUsername());
        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("StakHolder already exists with username: " + request.getUsername());
        }
        StackHolderDTO stackHolderDTO=new StackHolderDTO();
        stackHolderDTO.setFirstname(request.getFirstname());
        stackHolderDTO.setLastname(request.getLastname());
        stackHolderDTO.setUsername(request.getUsername());
        stackHolderDTO.setContact(request.getContact());
        stackHolderDTO.setEmail(request.getEmail());
        stackHolderDTO.setRole(request.getRole());
        stackHolderDTO.setPassword(passwordEncoder.encode(request.getPassword()));
        String token = jwtService.generateToken(this.StackDTOtoEntity(stackHolderDTO));
        return new AuthenticationResponse(token);
    }

    public LoginDTO EntityToDto(StakHolder stakHolder){
        LoginDTO loginDTO=new LoginDTO();
        loginDTO=modelMapper.map(stakHolder,LoginDTO.class);
        return loginDTO;
    }
    public StakHolder DTOtoEntity(LoginDTO loginDTO){
        StakHolder stakHolder=new StakHolder();
        stakHolder=modelMapper.map(loginDTO,StakHolder.class);
        return stakHolder;
    }
   public StackHolderDTO EntityToStackeDto(StakHolder stakHolder){
        StackHolderDTO stackHolderDTO=new StackHolderDTO();
        stackHolderDTO=modelMapper.map(stakHolder,StackHolderDTO.class);
        return stackHolderDTO;
   }
    public StakHolder StackDTOtoEntity(StackHolderDTO stackHolderDTO){
      StakHolder stakHolder=new StakHolder();
        stakHolder=modelMapper.map(stackHolderDTO,StakHolder.class);
        return stakHolder;
   }

}
