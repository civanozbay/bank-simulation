package com.banksimulation.mapper;

import com.banksimulation.dto.AccountDTO;
import com.banksimulation.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    private  final ModelMapper modelMapper;

    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AccountDTO convertToDTO(Account entity){
        // this method will accept Account entity and will convert it to DTO
        return modelMapper.map(entity, AccountDTO.class);
    }

    public Account convertToEntity(AccountDTO dto){
        // this method will accept AccountDTO  and will convert it to entity
        return modelMapper.map(dto, Account.class);
    }
}
