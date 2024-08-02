package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.Transaction;
import com.Ntra.ProGig.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo repo;

    public List<Transaction> getAllTransaction(){
        return this.repo.findAll();
    }
}
