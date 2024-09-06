package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Dto.JobDto;
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
        JobDto jobDto = this.JobToDto(jobs);
        return this.DtoToJob(jobDto);
    }


    public List<JobDto> getAllJobs (){
        List<Jobs> jobs = jobRepo.findAll();
        return jobs.stream().map(this::JobToDto).toList();
    }


    public JobDto getJobBYID(int id){

        Jobs jobs =this.jobRepo.findById(id);
        return this.JobToDto(jobs);
    }


    public List<JobDto> getJobByskillRequired(String skills){
        List<Jobs> jobs = this.jobRepo.findBySkillsRequired(skills);
        return jobs.stream().map(this::JobToDto).toList();

    }


    public List<JobDto> getJobBySkillsRequired(List<String> skills){
        List<Jobs> jobs = this.jobRepo.findBySkillsRequiredIn(skills);
        List<JobDto> jobDtos = jobs.stream().map(this::JobToDto).toList();
        return jobDtos;
    }


    public void deletebyid(int id) {
        jobRepo.deleteById((long) id);
    }

    public Jobs EditeJob(Jobs jobs){
        //if condition vapri ne user not found exception nakhva nu che
        Jobs exsistingJob = jobRepo.findById(jobs.getId());
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
