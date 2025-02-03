package com.Ntra.ProGig.Repository;

import com.Ntra.ProGig.Entity.BankDerails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDetailRepo extends JpaRepository<BankDerails, Integer> {
}
