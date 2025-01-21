package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Dto.JobDto;
import com.Ntra.ProGig.Entity.Jobs;
import com.Ntra.ProGig.Exception.NoContentException;
import com.Ntra.ProGig.Exception.UserNotFoundException;
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
    public int jobCount(){

    return (int) jobRepo.count();

    }
    public Jobs saveJobs (Jobs jobs)
    {
        try {
            JobDto jobDto = this.JobToDto(jobs);
            return this.DtoToJob(jobDto);
        } catch (Exception e) {
            throw new RuntimeException("Something Went Wrong!!");
        }
    }


    public List<JobDto> getAllJobs (){
        try {
            List<Jobs> jobs = jobRepo.findAll();
            return jobs.stream().map(this::JobToDto).toList();
        } catch (NoClassDefFoundError e) {
            throw new NoContentException("No such Content");
        }
    }


    public JobDto getJobBYID(int id){

        try {
            Jobs jobs =this.jobRepo.findById(id);
            return this.JobToDto(jobs);
        } catch (NoContentException e) {
            throw new NoContentException("ID not Found");
        }
    }


    public List<JobDto> getJobByskillRequired(String skills){
        try {
            List<Jobs> jobs = this.jobRepo.findBySkillsRequired(skills);
            return jobs.stream().map(this::JobToDto).toList();
        } catch (Exception e) {
            throw new RuntimeException("NO_SUCH_SKILL");
        }

    }


    public List<JobDto> getJobBySkillsRequired(List<String> skills){
        try {
            List<Jobs> jobs = this.jobRepo.findBySkillsRequiredIn(skills);
            List<JobDto> jobDtos = jobs.stream().map(this::JobToDto).toList();
            return jobDtos;
        } catch (Exception e) {
            throw new RuntimeException("NO_SUCH_SKILLS");
        }
    }


    public String deletebyid(int id) {
        try {
            jobRepo.deleteById((long) id);
            return "ID Delete"+id;
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("NO_SUCH_USER");
        }

    }

    public Jobs EditeJob(JobDto jobs){

        Jobs exsistingJob = null;
        try {
            exsistingJob = jobRepo.findById(jobs.getId());
        } catch (NoContentException e) {
            throw new NoContentException("NO_SUCH_JOB");
        }
        JobDto jobDto = this.JobToDto(exsistingJob);
        jobDto.setTitle(jobs.getTitle());
        jobDto.setAmount(jobs.getAmount());
        jobDto.setDescription(jobs.getDescription());
        jobDto.setDuration(jobs.getDuration());
        jobDto.setSkillsRequired(jobs.getSkillsRequired());
        jobDto.setPayout_methods(jobs.getPayout_methods());
        jobDto.setProviders_email(jobs.getProviders_email());
        jobDto.setProviders_name(jobs.getProviders_name());

        return this.DtoToJob(jobDto);
    }

    private JobDto JobToDto(Jobs jobs){
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
