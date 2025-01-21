package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Dto.ContractDto;
import com.Ntra.ProGig.Entity.Contract;
import com.Ntra.ProGig.Exception.NoContentException;
import com.Ntra.ProGig.Repository.ContractRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    @Autowired
    private ContractRepo repo;

    @Autowired
    private ModelMapper modelMapper;

    public List<ContractDto> getAllContract(){
        try {
            List<Contract> contracts = repo.findAll();
            return contracts.stream().map(this::UserToDto).toList();
        }catch (NoContentException e){
            throw new NoContentException("Empty Disc!!!");
        }

    }

    private ContractDto UserToDto(Contract contract){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ContractDto contractDto = new ContractDto();
        contractDto = new ModelMapper().map(contract, ContractDto.class);
        return contractDto;
    }

    private Contract DtoToUser(ContractDto contractDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Contract contract = new Contract();
        contract = new ModelMapper().map(contractDto, Contract.class);
        return contract;
    }
}
