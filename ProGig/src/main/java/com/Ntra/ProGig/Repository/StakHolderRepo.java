package com.Ntra.ProGig.Repository;

import com.Ntra.ProGig.Entity.StakHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StakHolderRepo extends JpaRepository<StakHolder,Integer> {
    @Override
    Optional<StakHolder> findById(Integer Id);

   Optional <StakHolder>findByUsername(String Username);
}
