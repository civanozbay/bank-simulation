package com.banksimulation.mapper;

import com.banksimulation.dto.AccountDTO;
import com.banksimulation.dto.TransactionDTO;
import com.banksimulation.entity.Account;
import com.banksimulation.entity.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    private  final ModelMapper modelMapper;


    public TransactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TransactionDTO convertToDTO(Transaction entity){
        // this method will accept Account entity and will convert it to DTO
        return modelMapper.map(entity, TransactionDTO.class);
    }

    public Transaction convertToEntity(TransactionDTO dto){
        // this method will accept AccountDTO  and will convert it to entity
        return modelMapper.map(dto, Transaction.class);
    }
}
