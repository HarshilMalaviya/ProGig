package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.Proposals;
import com.Ntra.ProGig.Repository.ProposalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProposalService {
    @Autowired
    private ProposalRepo repo;

    public Optional<Proposals> findByJobTitle(String jobTitle){
        return this.repo.findByJobTitle(jobTitle);
    }
}
