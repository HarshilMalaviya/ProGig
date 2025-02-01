package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Dto.ProposalsDto;
import com.Ntra.ProGig.Service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://192.168.0.168:5173/**")
@RequestMapping("/proposals")
public class ProposalController {

    @Autowired
    private ProposalService service;

    @GetMapping("/{jobTitle}")
    public ResponseEntity<ProposalsDto> getProposalByJobTitle(@PathVariable("jobTitle") String jobTitle){
        return ResponseEntity.ok(this.service.findByJobTitle(jobTitle));
    }
}
