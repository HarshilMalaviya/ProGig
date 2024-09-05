package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Dto.InvoiceDto;
import com.Ntra.ProGig.Dto.JobDto;
import com.Ntra.ProGig.Entity.Invoice;
import com.Ntra.ProGig.Entity.Jobs;
import com.Ntra.ProGig.Repository.JobRepo;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class JobsService {
//    private final SkillRepo skillRepo;

    @Autowired
    private final JobRepo jobRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Jobs saveJobs (Jobs jobs)
    {
        return jobRepo.save(jobs);
    }
    public List<Jobs> getAllJobs (){
        return jobRepo.findAll();
    }
    public Jobs getJobBYID(int id){
        return jobRepo.findById(id).orElse(null);
    }

    public List<Jobs> getJobByskillRequired(String skills){
        return  jobRepo.findBySkillsRequired(skills);
    }
    public List<Jobs> getJobByskilslRequired(List<String> skills){
        return  jobRepo.findBySkillsRequiredIn(skills);
    }
    public void deletebyid(int id) {
        jobRepo.deleteById((long) id);
    }

    public Jobs EditeSkills (Jobs jobs){
        Jobs exsistingJob = jobRepo.findById(jobs.getId()).orElse(null);
        exsistingJob.setTitle(jobs.getTitle());
        exsistingJob.setAmount(jobs.getAmount());
        exsistingJob.setDescription(jobs.getDescription());
        exsistingJob.setDuration(jobs.getDuration());
        exsistingJob.setSkillsRequired(jobs.getSkillsRequired());
        exsistingJob.setPayout_methods(jobs.getPayout_methods());
        exsistingJob.setProviders_email(jobs.getProviders_email());
        exsistingJob.setProviders_name(jobs.getProviders_name());

        return jobRepo.save(exsistingJob);
    }

    private JobDto jobToDto(Jobs jobs){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        JobDto jobDto = new JobDto();
        jobDto = new ModelMapper().map(jobs, JobDto.class);
        return jobDto;
    }

    private Jobs DtoToJob(JobDto jobDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Jobs jobs = new Jobs();
        jobs = new ModelMapper().map(jobDto, Jobs.class);
        return jobs;
    }


}
