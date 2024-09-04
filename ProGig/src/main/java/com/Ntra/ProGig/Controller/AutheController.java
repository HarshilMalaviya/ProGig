package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Dto.LoginDTO;
import com.Ntra.ProGig.Dto.StackHolderDTO;
import com.Ntra.ProGig.Entity.AuthenticationResponse;
import com.Ntra.ProGig.Entity.StakHolder;
import com.Ntra.ProGig.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class AutheController {

    private final AuthService authService;

    @PostMapping("/Login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDTO request){
        return ResponseEntity.ok(authService.authenticate(request));
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse>register(@RequestBody StackHolderDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
