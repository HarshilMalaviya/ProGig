package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Dto.ContractDto;
import com.Ntra.ProGig.Entity.Contract;
import com.Ntra.ProGig.Service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractService service;

    @GetMapping("/")
    public ResponseEntity<List<ContractDto>> getAllContracts(){
        return ResponseEntity.ok(this.service.getAllContract());
    }
}
