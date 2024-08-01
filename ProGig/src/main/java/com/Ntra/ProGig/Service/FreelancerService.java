package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.Role;
import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.Ntra.ProGig.Entity.Role.FREELANCER;

@Service
public class FreelancerService {
    @Autowired
    private UserRepo repo;

    public List<User> getAllFreelancer(){
        return this.repo.findAllByRole(Role.FREELANCER);
    }


    public Optional<User> getFreelancerByUsername(String username){
        return this.repo.findByUsernameAndRole(username,Role.FREELANCER);
    }

    public Optional<User> getFreelancerByEmail(String email){
        return this.repo.findByEmailAndRole(email,Role.FREELANCER);
    }

    public Optional<User> getFreelancerById(Integer id){
        return this.repo.findByIdAndRole(id,Role.FREELANCER);
    }

    public void deleteFreelancer(Integer id){
        repo.deleteById(id);
    }

    public User acceptFreelancer(Integer id){
        Optional<User> freelancer= this.repo.findById(id);
        if (freelancer.isPresent()){
            User user = freelancer.get();
            user.setStatus("ACCEPTED");
            return this.repo.save(user);
        }
        return null;
    }

    public User rejectFreelancer(Integer id,String description){
        Optional<User> freelancer= this.repo.findById(id);
        if (freelancer.isPresent()){
            User user= freelancer.get();
            user.setStatus("REJECTED");
            user.setWhyRejected(description);
            return this.repo.save(user);
        }
        return null;
    }
}
