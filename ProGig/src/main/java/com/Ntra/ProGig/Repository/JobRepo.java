package com.Ntra.ProGig.Repository;

import com.Ntra.ProGig.Entity.Jobs;
import org.hibernate.jdbc.Expectation;
import org.hibernate.jdbc.Expectation.RowCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepo extends JpaRepository<Jobs,Long> {

  List<Jobs>findBySkillsRequiredIn(List<String> skills);
  List<Jobs> findBySkillsRequired(String skillsRequired);
  Jobs findById(Integer integer);

}
