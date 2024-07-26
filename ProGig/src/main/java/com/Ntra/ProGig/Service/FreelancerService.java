package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FreelancerService {
    @Autowired
    private UserRepo repo;

    public List<User> getAllFreelancer(){
        return this.repo.findAll();
    }

    public Optional<User> getFreelancerByUsername(String username){
        return this.repo.findByUsername(username);
    }

    public Optional<User> getFreelancerByEmail(String email){
        return this.repo.findByEmail(email);
    }

    public Optional<User> getFreelancerById(Integer id){
        return this.repo.findById(id);
    }

    public void deleteFreelancer(Integer id){
        repo.deleteById(id);
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
}
