package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Entity.Dispute;
import com.Ntra.ProGig.Entity.DisputeStatus;
import com.Ntra.ProGig.Service.DisputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/disputes")
public class DisputeController {

    @Autowired
    private DisputeService disputeService;

    @PostMapping
    public ResponseEntity<Dispute> createDispute(@RequestBody Dispute dispute) {
        return ResponseEntity.ok(disputeService.createDispute(dispute));
    }

    @GetMapping
    public ResponseEntity<List<Dispute>> getAllDisputes() {
        return ResponseEntity.ok(disputeService.getAllDisputes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Dispute>> getDisputeById(@PathVariable Long id) {
        return ResponseEntity.ok(disputeService.getDisputeById(id));
    }

    @GetMapping("/admin/{adminId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Dispute>> getDisputesByAdmin(@PathVariable Long adminId) {
        return ResponseEntity.ok(disputeService.getDisputesByAdmin(adminId));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Dispute> updateDisputeStatus(@PathVariable Long id, @RequestParam DisputeStatus status, @RequestParam String resolution, @RequestParam Long adminId) {
        return ResponseEntity.ok(disputeService.updateDisputeStatus(id, status, resolution, adminId));
    }
}
