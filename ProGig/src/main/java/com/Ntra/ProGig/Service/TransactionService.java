package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Dto.TransactionDto;
import com.Ntra.ProGig.Entity.Transaction;
import com.Ntra.ProGig.Exception.NoContentException;
import com.Ntra.ProGig.Repository.TransactionRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo repo;

    @Autowired
    private ModelMapper modelMapper;

    public List<TransactionDto> getAllTransaction() {
        try {

            List<Transaction> transaction = this.repo.findAll();
            return transaction.stream().map(this::TransactionToDto).toList();

        } catch (NoContentException e) {
            throw new NoContentException("There is not any data present yet");
        }
    }
    private TransactionDto TransactionToDto(Transaction transaction){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        TransactionDto transactionDto = new TransactionDto();
        transactionDto = new ModelMapper().map(transaction, TransactionDto.class);
        return transactionDto;
    }

    private Transaction DtoToTransaction(TransactionDto transactionDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Transaction transaction = new Transaction();
        transaction = new ModelMapper().map(transactionDto, Transaction.class);
        return transaction;
    }
}
