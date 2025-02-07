package com.Ntra.ProGig.Repository;
import com.Ntra.ProGig.Entity.Dispute;
import com.Ntra.ProGig.Entity.DisputeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisputeRepo extends JpaRepository<Dispute, Long> {
    List<Dispute> findByStatus(DisputeStatus status);
    List<Dispute> findByResolvedByAdminId(Long adminId);
}