package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.Millstone;
import com.Ntra.ProGig.Repository.MillstoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MillstoneService {
    @Autowired
    private MillstoneRepo repo;

    public List<Millstone> findAllMilestone(){
        return this.repo.findAll();
    }

    public Optional<Millstone> findByJobTitle(String jobTitle){
        return this.repo.findMillstoneByJobTitle(jobTitle);
    }
}
