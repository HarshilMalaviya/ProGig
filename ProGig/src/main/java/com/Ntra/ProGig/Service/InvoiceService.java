package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Dto.InvoiceDto;
import com.Ntra.ProGig.Entity.Invoice;
import com.Ntra.ProGig.Exception.NoContentException;
import com.Ntra.ProGig.Repository.InvoiceRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    @Autowired
    private final InvoiceRepo invoiceRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Invoice saveInvoice (InvoiceDto invoiceDto){

        return this.DtoToInvoice(invoiceDto);

    }
    public List<InvoiceDto> getAllInvoice (){

        try {
            List<Invoice> invoices = invoiceRepo.findAll();
            return invoices.stream().map(this::InvoiceToDto).toList();
        } catch (NoContentException e) {
            throw new NoContentException("No_Content");
        }
    }
    public List<InvoiceDto> getById(Integer id){

        try {
            Optional<Invoice> invoice =this.invoiceRepo.findById(id);
            List<InvoiceDto> invoiceDto = invoice.map(this::InvoiceToDto).stream().toList();
            return invoiceDto;
        } catch (NoContentException e) {
            throw new NoContentException("No_Content");
        }
    }

    private InvoiceDto InvoiceToDto(Invoice invoice){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto = new ModelMapper().map(invoice, InvoiceDto.class);
        return invoiceDto;
    }

    private Invoice DtoToInvoice(InvoiceDto invoiceDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Invoice invoice = new Invoice();
        invoice = new ModelMapper().map(invoiceDto, Invoice.class);
        return invoice;
    }

}
