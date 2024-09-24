package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Dto.ProposalsDto;
import com.Ntra.ProGig.Entity.Proposals;
import com.Ntra.ProGig.Exception.UserNotFoundException;
import com.Ntra.ProGig.Repository.ProposalRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposalService {
    @Autowired
    private ProposalRepo repo;

    @Autowired
    private ModelMapper modelMapper;

    public ProposalsDto findByJobTitle(String jobTitle){
        try {
            Proposals proposals = this.repo.findByJobTitle(jobTitle);
            return this.ProposalsToDto(proposals);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("there is no proposal present");
        }
    }

    private ProposalsDto ProposalsToDto(Proposals proposals){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ProposalsDto proposalsDto = new ProposalsDto();
        proposalsDto = new ModelMapper().map(proposals, ProposalsDto.class);
        return proposalsDto;
    }

    private Proposals DtoToProposals(ProposalsDto proposalsDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Proposals proposals = new Proposals();
        proposals = new ModelMapper().map(proposalsDto, Proposals.class);
        return proposals;
    }
}
