package com.Ntra.ProGig.Repository;

import com.Ntra.ProGig.Entity.Proposals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProposalRepo extends JpaRepository<Proposals,Integer> {
    Proposals findByJobTitle(String jobTitle);
}
