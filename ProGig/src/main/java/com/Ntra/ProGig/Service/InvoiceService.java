package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.Invoice;
import com.Ntra.ProGig.Repository.InvoiceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    @Autowired
    private final InvoiceRepo invoiceRepo;

    public Invoice saveInvoice (Invoice invoice){
        return invoiceRepo.save(invoice);
    }
    public List<Invoice> getAllInvoice (){
        return invoiceRepo.findAll();
    }
    public Optional<Invoice> getById(Integer id){
        return invoiceRepo.findById(id);
    }

}
