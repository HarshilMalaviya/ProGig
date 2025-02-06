package com.Ntra.ProGig.Repository;

import com.Ntra.ProGig.Dto.JobDto;
import com.Ntra.ProGig.Entity.Jobs;
import org.hibernate.jdbc.Expectation;
import org.hibernate.jdbc.Expectation.RowCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepo extends JpaRepository<Jobs,Long> {

  List<Jobs>findBySkillsRequiredIn(List<String> skills);
  List<Jobs> findBySkillsRequired(String skillsRequired);
  Jobs findById(Integer integer);

//  @Query("SELECT j from Jobs j WHERE " +
//          "LOWER(j.title) LIKE LOWER(CONCAT('%',:keyword,'%')) OR" )
//  List<JobDto> search(String keyword);
  @Query("SELECT j FROM Jobs j WHERE " +
        "LOWER(j.title) LIKE LOWER(CONCAT('%',:keyword,'%')) OR " +
        "LOWER(j.description) LIKE LOWER(CONCAT('%',:keyword,'%')) OR " +
        "LOWER(STR(j.amount)) LIKE LOWER(CONCAT('%',:keyword,'%')) OR " +
        "LOWER(j.providers_name) LIKE LOWER(CONCAT('%',:keyword,'%')) OR " +
        "LOWER(j.providers_email) LIKE LOWER(CONCAT('%',:keyword,'%')) OR " +
        ":keyword MEMBER OF j.skillsRequired")
  List<Jobs> search(String keyword);

    Jobs findByTitle(String title);
}
