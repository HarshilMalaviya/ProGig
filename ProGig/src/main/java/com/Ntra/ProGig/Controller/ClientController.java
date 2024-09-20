package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Dto.UserDto;
import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/getAllClients")
    public ResponseEntity<List<UserDto>> getAllClients() {
        return ResponseEntity.ok(this.clientService.getAllClients());
    }

    @GetMapping("/{id}")

    public ResponseEntity<UserDto> getClientById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.clientService.getClientById(id));
    }
      
    @GetMapping("/username/{username}")
    public UserDto getClientByUsername(@PathVariable String username) {
        return this.clientService.getClientByUsername(username);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getClientByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(this.clientService.getClientByEmail(email));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/{id}/accept")
    public ResponseEntity<User> acceptClient(@PathVariable Integer id) {
        User acceptedClient = clientService.acceptClient(id);
        return acceptedClient != null ? ResponseEntity.ok(acceptedClient) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<User> rejectClient(@PathVariable Integer id, @RequestBody String description) {
        User rejectedClient = clientService.rejectClient(id, description);
        return rejectedClient != null ? ResponseEntity.ok(rejectedClient) : ResponseEntity.notFound().build();
    }
}

