package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Dto.SkillsDto;
import com.Ntra.ProGig.Entity.Skills;
import com.Ntra.ProGig.Repository.SkillRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class SkillService {
    @Autowired
    private final SkillRepo skillRepo;

    @Autowired
    private ModelMapper modelMapper;


    public Skills saveSkill(SkillsDto skillsDto){

        return this.DtoToSkills(skillsDto);
    }


    public List<SkillsDto> getAllSkills()
    {

        List<Skills> skills = this.skillRepo.findAll();
        return skills.stream().map(this::SkillsToDto).toList();
    }


    public SkillsDto getSkillsbyid(int id)
    {

        Skills skills = this.skillRepo.findById(id).orElse(null);
        return this.SkillsToDto(skills);
    }


    public void deletebyid(int id) {

        skillRepo.deleteById(id);
    }


    public Skills EditeSkills (SkillsDto skillsDto){
        SkillsDto existiogSkillsDto = new SkillsDto();
        existiogSkillsDto.setSkillName(skillsDto.getSkillName());

        return this.DtoToSkills(existiogSkillsDto);
    }

    private SkillsDto SkillsToDto(Skills skills){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        SkillsDto skillsDto = new SkillsDto();
        skillsDto = new ModelMapper().map(skills, SkillsDto.class);
        return skillsDto;
    }

    private Skills DtoToSkills(SkillsDto skillsDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Skills skills = new Skills();
        skills = new ModelMapper().map(skillsDto, Skills.class);
        return skills;
    }
}
