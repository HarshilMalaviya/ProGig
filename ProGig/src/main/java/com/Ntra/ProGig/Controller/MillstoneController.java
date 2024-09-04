package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Entity.Millstone;
import com.Ntra.ProGig.Service.MillstoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/millstone")
public class MillstoneController {
    @Autowired
    private MillstoneService service;

    @GetMapping("/getAll")
    public ResponseEntity<List<Millstone>> getAllMillstone(){
        return ResponseEntity.ok(this.service.findAllMilestone());
    }

    @GetMapping("/getByJobTitle/{jobTitle}")
    public ResponseEntity<Optional<Millstone>> getByJobTitle(@PathVariable("jobTitle") String jobTitle){
        return ResponseEntity.ok(this.service.findByJobTitle(jobTitle));
    }
}
