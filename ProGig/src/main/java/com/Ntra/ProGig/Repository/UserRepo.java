package com.Ntra.ProGig.Repository;

import com.Ntra.ProGig.Entity.Role;
import com.Ntra.ProGig.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface UserRepo extends JpaRepository<User,Integer> {

    List<User> findAllByRole(Role role);
    Optional<User> findByUsernameAndRole(String username, Role role);
    Optional<User> findByEmailAndRole(String email, Role role);
    Optional<User> findByIdAndRole(Integer id, Role role);

}
