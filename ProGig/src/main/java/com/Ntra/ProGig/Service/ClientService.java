package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.Role;
import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private UserRepo repo;

    public List<User> getAllClients(){
        return this.repo.findAllByRole(Role.CLIENT);
    }

    public Optional<User> getClientByUsername(String username){
        return this.repo.findByUsernameAndRole(username, Role.CLIENT);
    }

    public Optional<User> getClientByEmail(String email){
        return this.repo.findByEmailAndRole(email, Role.CLIENT);
    }

    public Optional<User> getClientById(Integer id){
        return this.repo.findByIdAndRole(id, Role.CLIENT);
    }

    public void deleteClient(Integer id){
        repo.deleteById(id);
    }
}

