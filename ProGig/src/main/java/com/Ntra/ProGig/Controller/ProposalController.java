package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Entity.Proposals;
import com.Ntra.ProGig.Service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/proposals")
public class ProposalController {

    @Autowired
    private ProposalService service;

    @GetMapping("/{jobTitle}")
    public ResponseEntity<Optional<Proposals>> getProposalByJobTitle(@PathVariable("jobTitle") String jobTitle){
        return ResponseEntity.ok(this.service.findByJobTitle(jobTitle));
    }
}
