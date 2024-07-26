package com.Ntra.ProGig.Repository;

import com.Ntra.ProGig.Entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepo extends JpaRepository<Jobs,Long> {

  List<Jobs> findBySkillsRequired(String skillsRequired);
  Optional<Jobs> findById(Integer integer);
}
