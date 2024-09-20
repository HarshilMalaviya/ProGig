package com.Ntra.ProGig.Repository;

import com.Ntra.ProGig.Entity.Millstone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MillstoneRepo extends JpaRepository<Millstone,Integer> {
    Millstone findMillstoneByJobTitle(String jobTitle);
}
