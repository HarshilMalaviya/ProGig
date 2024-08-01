package com.Ntra.ProGig.Controller;

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
    public List<User> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getClientById(@PathVariable Integer id) {
        Optional<User> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getClientByUsername(@PathVariable String username) {
        Optional<User> client = clientService.getClientByUsername(username);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getClientByEmail(@PathVariable String email) {
        Optional<User> client = clientService.getClientByEmail(email);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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

