package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Entity.Skills;
import com.Ntra.ProGig.Repository.SkillRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class SkillService {
    @Autowired
    private final SkillRepo skillRepo;
    public Skills saveSkill(Skills skills){
        return skillRepo.save(skills);
    }

    public List<Skills> getAllSkills()
    {
        return skillRepo.findAll();
    }
    public Skills getSkillsbyid(int id)
    {
        return skillRepo.findById(id).orElse(null);
    }
    public void deletebyid(int id) {
        skillRepo.deleteById(id);
    }
    public Skills EditeSkills (Skills skills){
        Skills exsistingSkills=skillRepo.findById(skills.getId()).orElse(null);
        exsistingSkills.setSkillName(skills.getSkillName());

        return skillRepo.save(exsistingSkills);
    }
}
