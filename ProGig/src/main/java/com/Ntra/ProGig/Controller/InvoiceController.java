package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Dto.InvoiceDto;
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
    public ResponseEntity<List<InvoiceDto>> getAllInvoice()
    {   List<InvoiceDto> list = invoiceService.getAllInvoice();
        if(list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.of(Optional.of(list));
        }
    }
    @GetMapping("/InvoiceById/{id}")
    public ResponseEntity<List<InvoiceDto>> findByJInvoiceBYId(@PathVariable int id) {
        List<InvoiceDto> invoice=invoiceService.getById(id);
        if(invoice.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(invoice));
        }
    }

}
