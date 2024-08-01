package com.Ntra.ProGig.Repository;

import com.Ntra.ProGig.Entity.Skills;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SkillRepo extends JpaRepository<Skills,Integer> {
    Optional<Skills> findBySkillName(String SkillName);

    @Override
    Optional<Skills> findById(Integer integer);
}

