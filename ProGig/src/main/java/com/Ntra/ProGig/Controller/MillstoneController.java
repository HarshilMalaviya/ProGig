package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Dto.MillstoneDto;
import com.Ntra.ProGig.Service.MillstoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/millstone")
@CrossOrigin("http://192.168.1.16:5173/**")
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
