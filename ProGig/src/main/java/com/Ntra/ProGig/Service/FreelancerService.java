package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.Freelancer;
import com.Ntra.ProGig.Repository.FreelancerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FreelancerService {
    @Autowired
    private  FreelancerRepo repo;

    public List<Freelancer> getAllFreelancer(){
        return this.repo.findAll();
    }

    public Optional<Freelancer> getFreelancerByUsername(String username){
        return this.repo.findByUsername(username);
    }

    public Optional<Freelancer> getFreelancerByEmail(String email){
        return this.repo.findByEmail(email);
    }

    public Optional<Freelancer> getFreelancerById(Integer id){
        return this.repo.findById(id);
    }

    public void deleteFreelancer(Integer id){
        repo.deleteById(id);
    }

    public Freelancer saveUser(Freelancer freelancer) {
        Freelancer createFreelancer = this.repo.save(freelancer);
        createFreelancer.setId(freelancer.getId());
        createFreelancer.setFirstName(freelancer.getFirstName());
        createFreelancer.setLastName(freelancer.getLastName());
        createFreelancer.setEmail(freelancer.getEmail());
        createFreelancer.setUsername(freelancer.getUsername());
        createFreelancer.setPassword(freelancer.getPassword());
        createFreelancer.setDescription(freelancer.getDescription());
        createFreelancer.setDescription(freelancer.getDescription());
        return createFreelancer;
    }
}
