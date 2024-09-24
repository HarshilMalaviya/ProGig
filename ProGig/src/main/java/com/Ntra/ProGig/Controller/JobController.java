package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Dto.JobDto;
import com.Ntra.ProGig.Entity.Jobs;
import com.Ntra.ProGig.Service.JobsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobController {
    private final JobsService jobsService;
    @PostMapping("/addjobs")
    public Jobs addjobs(@RequestBody Jobs jobs){
        return   jobsService.saveJobs(jobs);
    }
    @GetMapping("/Jobs")
    public ResponseEntity<List<JobDto>> getAllJobs()
    {   List<JobDto> list = jobsService.getAllJobs();
        if(list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.of(Optional.of(list));
        }
    }
    @GetMapping("/job_id/{id}")
    public ResponseEntity<JobDto> findbyJobId(@PathVariable int id) {
      JobDto jobDto=jobsService.getJobBYID(id);
        if(jobDto==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(jobDto));
        }
    }
    @GetMapping("/job/skill/{skill}")
    public ResponseEntity<List<JobDto>> findJobbySkills(@PathVariable String skill) {
       List<JobDto> jobs=jobsService.getJobByskillRequired(skill);
        if(jobs==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(jobs));
        }
    }
    @GetMapping("/job/skills")
    public ResponseEntity<List<JobDto>> findJobBySkills(@RequestParam(value = "skill") List<String> skill) {
        List<JobDto> jobs=jobsService.getJobBySkillsRequired(skill);
        if(jobs==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(jobs));
        }
    }

    @GetMapping("/Count")
    public int Count(){
        return jobsService.jobCount();
    }


    @PutMapping("/updateJob")
    public ResponseEntity<Jobs> EditeJobs(@RequestBody JobDto job){
        Jobs jobs = jobsService.EditeJob(job);
        return ResponseEntity.of(Optional.of(jobs));
    }
    @DeleteMapping("/deleteJob/{id}")
    public ResponseEntity<Void> deleteJobs(@PathVariable int id){
        jobsService.deletebyid(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
