package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Dto.MillstoneDto;
import com.Ntra.ProGig.Service.MillstoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://192.168.254.250:5173/**")
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
