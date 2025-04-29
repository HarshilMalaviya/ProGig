package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.Dispute;
import com.Ntra.ProGig.Entity.DisputeStatus;
import com.Ntra.ProGig.Repository.DisputeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DisputeService {
    @Autowired
    private DisputeRepo disputeRepository;

    public Dispute createDispute(Dispute dispute) {
        dispute.setStatus(DisputeStatus.PENDING);
        return disputeRepository.save(dispute);
    }

    public List<Dispute> getAllDisputes() {
        return disputeRepository.findAll();
    }

    public Optional<Dispute> getDisputeById(Long id) {
        return disputeRepository.findById(id);
    }

    public List<Dispute> getDisputesByAdmin(Long adminId) {
        return disputeRepository.findByResolvedByAdminId(adminId);
    }

    public Dispute updateDisputeStatus(Long id, DisputeStatus status, String resolution, Long adminId) {
        Dispute dispute = disputeRepository.findById(id).orElseThrow(() -> new RuntimeException("Dispute not found"));
        dispute.setStatus(status);
        dispute.setResolution(resolution);
        dispute.setResolvedByAdminId(adminId);
        dispute.setResolvedAt(LocalDateTime.now());
        return disputeRepository.save(dispute);
    }
}