package com.Ntra.ProGig.Repository;

import com.Ntra.ProGig.Entity.UserRole;
import com.Ntra.ProGig.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface UserRepo extends JpaRepository<User,Integer> {

    List<User> findAllByRole(UserRole role);
    User findByUsernameAndRole(String username, UserRole role);
    User findByEmailAndRole(String email, UserRole role);
    User findByIdAndRole(Integer id, UserRole role);

}
