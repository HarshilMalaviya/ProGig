package com.Ntra.ProGig.Repository;

import com.Ntra.ProGig.Entity.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FreelancerRepo extends JpaRepository<Freelancer,Integer> {

    Optional<Freelancer> findByUsername(String username);

    Optional<Freelancer> findByEmail(String email);
}
