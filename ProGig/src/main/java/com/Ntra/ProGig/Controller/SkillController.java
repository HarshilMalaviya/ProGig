package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Entity.Skills;
import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Service.SkillService;
import com.Ntra.ProGig.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;
    @PostMapping ("/addskill")
    public Skills addSkill(@RequestBody Skills skills){
        return   skillService.saveSkill(skills);
    }
    @GetMapping("/skills")
    public ResponseEntity<List<Skills>> getAllSkills()
    {   List<Skills> list = skillService.getAllSkills();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.of(Optional.of(list));
        }
    }
    @GetMapping("/skills/{id}")
    public ResponseEntity<Skills> findbySkillid(@PathVariable int id) {
        Skills skills=skillService.getSkillsbyid(id);
        if(skills==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(skills));
        }
    }
    @PutMapping("updateskill")
    public ResponseEntity<Skills> EditeSkills(@RequestBody Skills skill1){
        Skills skills = skillService.EditeSkills(skill1);
        return ResponseEntity.of(Optional.of(skills));
    }
    @DeleteMapping("/deletskill/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable int id){
        skillService.deletebyid(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
