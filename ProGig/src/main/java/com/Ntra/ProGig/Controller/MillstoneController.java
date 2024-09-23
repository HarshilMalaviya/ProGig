package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Dto.MillstoneDto;
import com.Ntra.ProGig.Service.MillstoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/millstone")
public class MillstoneController {
    @Autowired
    private MillstoneService service;

    @GetMapping("/getAll")
    public ResponseEntity<List<MillstoneDto>> getAllMillstone(){
        return ResponseEntity.ok(this.service.findAllMilestone());
    }

    @GetMapping("/getByJobTitle/{jobTitle}")
    public ResponseEntity<MillstoneDto> getByJobTitle(@PathVariable("jobTitle") String jobTitle){
        return ResponseEntity.ok(this.service.findByJobTitle(jobTitle));
    }
}
