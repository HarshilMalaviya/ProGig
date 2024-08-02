package com.Ntra.ProGig.Repository;

import com.Ntra.ProGig.Entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice,Integer> {
    @Override
    List<Invoice> findAllById(Iterable<Integer> integers);
}
