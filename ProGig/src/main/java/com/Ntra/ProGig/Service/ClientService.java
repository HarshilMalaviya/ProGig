package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.UserRole;
import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Exception.NoContentException;
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
        try {
            return this.repo.findAllByRole(UserRole.CLIENT);
        }
        catch (NoContentException e){
            throw new NoContentException("There is no such Content");
        }
    }

    public Optional<User> getClientByUsername(String username){
        return this.repo.findByUsernameAndRole(username, UserRole.CLIENT);
    }

    public Optional<User> getClientByEmail(String email){
        return this.repo.findByEmailAndRole(email, UserRole.CLIENT);
    }

    public Optional<User> getClientById(Integer id){
        return this.repo.findByIdAndRole(id, UserRole.CLIENT);
    }

    public void deleteClient(Integer id){
        repo.deleteById(id);
    }

    public User acceptClient(Integer id){
        Optional<User> client= this.repo.findById(id);
        if (client.isPresent()){
            User user = client.get();
            user.setStatus("ACCEPTED");
            return this.repo.save(user);
        }
        return null;
    }

    public User rejectClient(Integer id,String description){
        Optional<User> client= this.repo.findById(id);
        if (client.isPresent()){
            User user= client.get();
            user.setStatus("REJECTED");
            user.setWhyRejected(description);
            return this.repo.save(user);
        }
        return null;
    }
}

