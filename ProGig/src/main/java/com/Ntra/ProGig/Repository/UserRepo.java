package com.Ntra.ProGig.Repository;

import com.Ntra.ProGig.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    @Override
    Optional<User> findById(Integer Id);

   Optional <User>findByUsername(String Username);
}
