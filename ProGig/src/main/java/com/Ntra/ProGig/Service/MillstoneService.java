package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Dto.MillstoneDto;
import com.Ntra.ProGig.Entity.Millstone;
import com.Ntra.ProGig.Exception.NoContentException;
import com.Ntra.ProGig.Exception.UserNotFoundException;
import com.Ntra.ProGig.Repository.MillstoneRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MillstoneService {
    @Autowired
    private MillstoneRepo repo;

    @Autowired
    private ModelMapper modelMapper;

    public List<MillstoneDto> findAllMilestone() {
        try {
            List<Millstone> millstones = repo.findAll();
            return millstones.stream().map(this::MillstoneToDto).toList();
        } catch (NoContentException e) {
            throw new NoContentException("Milestone data is not present");
        }
    }

    public MillstoneDto findByJobTitle(String jobTitle) {
        try {
            Millstone millstone = this.repo.findMillstoneByJobTitle(jobTitle);
            return this.MillstoneToDto(millstone);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Milestone is not present");
        }
    }

    private MillstoneDto MillstoneToDto(Millstone millstone){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        MillstoneDto millstoneDto = new MillstoneDto();
        millstoneDto = new ModelMapper().map(millstone, MillstoneDto.class);
        return millstoneDto;
    }

    private Millstone DtoToMillstone(MillstoneDto millstoneDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Millstone millstone = new Millstone();
        millstone = new ModelMapper().map(millstoneDto, Millstone.class);
        return millstone;
    }
}
