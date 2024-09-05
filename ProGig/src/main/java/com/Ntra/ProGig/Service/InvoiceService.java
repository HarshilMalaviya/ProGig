package com.Ntra.ProGig.Service;

import com.Ntra.ProGig.Dto.InvoiceDto;
import com.Ntra.ProGig.Entity.Invoice;
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

    public Invoice saveInvoice (Invoice invoice){

        InvoiceDto invoiceDto = this.InvoiceToDto(invoice);
        return this.DtoToInvoice(invoiceDto);

    }
    public List<InvoiceDto> getAllInvoice (){

        List<Invoice> invoices = invoiceRepo.findAll();
        return invoices.stream().map(this::InvoiceToDto).toList();
    }
    public List<InvoiceDto> getById(Integer id){

        Optional<Invoice> invoice =this.invoiceRepo.findById(id);
        List<InvoiceDto> invoiceDto = invoice.map(this::InvoiceToDto).stream().toList();
        return invoiceDto;
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
