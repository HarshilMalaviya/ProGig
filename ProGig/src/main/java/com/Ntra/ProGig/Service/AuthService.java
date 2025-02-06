package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Dto.LoginDTO;
import com.Ntra.ProGig.Dto.StackHolderDTO;
import com.Ntra.ProGig.Entity.AuthenticationResponse;
import com.Ntra.ProGig.Entity.Role;
import com.Ntra.ProGig.Entity.StakHolder;
import com.Ntra.ProGig.Exception.SecurityException;
import com.Ntra.ProGig.Exception.UserAlreadyExistsException;
import com.Ntra.ProGig.Repository.StakHolderRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



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
        StakHolder user=userRepo.findByUsername(request.getUsername());

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }


    public AuthenticationResponse register(StackHolderDTO request) {
        // Get the logged-in user from SecurityContext
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof UserDetails)) {
            throw new SecurityException("Unauthorized access! No valid user found.");
        }

        String loggedInUsername = ((UserDetails) principal).getUsername();
        StakHolder loggedInUserEntity = userRepo.findByUsername(loggedInUsername);

        if (loggedInUserEntity == null) {
            throw new SecurityException("Logged-in user not found!");
        }

        // Check if the username is already taken
        if (userRepo.findByUsername(request.getUsername()) != null) {
            throw new UserAlreadyExistsException("User already exists!");
        }

        // Role-based validation
        if (request.getRole() == Role.SUPER_ADMIN) {
            throw new SecurityException("Super Admin creation is not allowed!");
        }

        if (request.getRole() == Role.ADMIN && loggedInUserEntity.getRole() != Role.SUPER_ADMIN) {
            throw new SecurityException("Only Super Admins can register Admins!");
        }

        if (request.getRole() == Role.EMPLOYEE && loggedInUserEntity.getRole() == Role.EMPLOYEE) {
            throw new SecurityException("Employees cannot register new users!");
        }

        // Create new user
        StakHolder newUser = modelMapper.map(request, StakHolder.class);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        // Save user & generate JWT token
        String token = jwtService.generateToken(userRepo.save(newUser));
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
