package com.Ntra.ProGig.Controller;

import com.Ntra.ProGig.Dto.SkillsDto;
import com.Ntra.ProGig.Entity.Skills;
import com.Ntra.ProGig.Service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Skills")
public class SkillController {
    private final SkillService skillService;
    @GetMapping("/Count")
    public int Count(){
        return skillService.SkillCount();
    }
    @PostMapping ("/addskill")
    public Skills addSkill(@RequestBody SkillsDto skillsDto){
        return   skillService.saveSkill(skillsDto);
    }
    @GetMapping("/skills")
    public ResponseEntity<List<SkillsDto>> getAllSkills()
    {   List<SkillsDto> list = skillService.getAllSkills();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.of(Optional.of(list));
        }
    }
    @GetMapping("/skills/{id}")
    public ResponseEntity<SkillsDto> findbySkillid(@PathVariable int id) {
        SkillsDto skills=skillService.getSkillsbyid(id);
        if(skills==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(skills));
        }
    }
    @PutMapping("updateskill")
    public ResponseEntity<Skills> EditeSkills(@RequestBody SkillsDto skillsDto){
        Skills skills = skillService.EditeSkills(skillsDto);
        return ResponseEntity.of(Optional.of(skills));
    }
    @DeleteMapping("/deletskill/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable int id){
        skillService.deletebyid(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
