package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.Contract;
import com.Ntra.ProGig.Repository.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    @Autowired
    private ContractRepo repo;

    public List<Contract> getAllContract(){
        return this.repo.findAll();
    }
}
