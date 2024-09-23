package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Dto.TransactionDto;
import com.Ntra.ProGig.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Transaction")
public class TransactionController {
    @Autowired
    private TransactionService service;

    @GetMapping("/getAll")
    public ResponseEntity<List<TransactionDto>> getAllTransaction(){
        return ResponseEntity.ok(this.service.getAllTransaction());

    }
}
