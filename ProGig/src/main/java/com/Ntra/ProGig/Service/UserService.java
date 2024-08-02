package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

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
