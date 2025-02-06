package com.Ntra.ProGig.Repository;

import com.Ntra.ProGig.Entity.UserRole;
import com.Ntra.ProGig.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface UserRepo extends JpaRepository<User,Integer> {

    List<User> findAllByRole(UserRole role);
    User findByUsernameAndRole(String username, UserRole role);
    User findByEmailAndRole(String email, UserRole role);
    User findByIdAndRole(Integer id, UserRole role);

    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM User u WHERE " +
            "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(STR(u.role)) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.status) LIKE LOWER(CONCAT('%', :keyword, '%')) ")
    List<User> search( String keyword);
}
