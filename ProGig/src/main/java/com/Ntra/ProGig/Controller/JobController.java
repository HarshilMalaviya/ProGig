package com.Ntra.ProGig.Controller;

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
    public ResponseEntity<List<Jobs>> getAllJobs()
    {   List<Jobs> list = jobsService.getAllJobs();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.of(Optional.of(list));
        }
    }
    @GetMapping("/job_id/{id}")
    public ResponseEntity<Jobs> findbyJobId(@PathVariable int id) {
      Jobs jobs=jobsService.getJobBYID(id);
        if(jobs==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(jobs));
        }
    }
    @GetMapping("/job/skill/{skill}")
    public ResponseEntity<List<Jobs>> findJobbySkills(@PathVariable String skill) {
       List<Jobs> jobs=jobsService.getJobByskillRequired(skill);
        if(jobs==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(jobs));
        }
    }
    @GetMapping("/job/skills")
    public ResponseEntity<List<Jobs>> findJobBySkills(@RequestParam(value = "skill") List<String> skill) {
        List<Jobs> jobs=jobsService.getJobByskilslRequired(skill);
        if(jobs==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(jobs));
        }
    }


    @PutMapping("/updateJob")
    public ResponseEntity<Jobs> EditeJobs(@RequestBody Jobs job){
        Jobs jobs = jobsService.EditeSkills(job);
        return ResponseEntity.of(Optional.of(jobs));
    }
    @DeleteMapping("/deleteJob/{id}")
    public ResponseEntity<Void> deleteJobs(@PathVariable int id){
        jobsService.deletebyid(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
