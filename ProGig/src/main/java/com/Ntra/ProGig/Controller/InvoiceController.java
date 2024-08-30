package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Entity.Invoice;
import com.Ntra.ProGig.Service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Invoice")
public class InvoiceController {
    @Autowired
    private final InvoiceService invoiceService;
    @PostMapping("/addInvoice")
    public Invoice addInvoice(@RequestBody Invoice invoice){
        return   invoiceService.saveInvoice(invoice);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Invoice>> getAllInvoice()
    {   List<Invoice> list = invoiceService.getAllInvoice();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.of(Optional.of(list));
        }
    }
    @GetMapping("/InvoiceById/{id}")
    public ResponseEntity<Optional<Invoice>> findbyJInvoiceBYId(@PathVariable int id) {
        Optional<Invoice> invoice=invoiceService.getById(id);
        if(invoice==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(invoice));
        }
    }

}
