package com.summerHack.diningTogether.Converter;

import com.summerHack.diningTogether.DTO.ApplicationDTO;
import com.summerHack.diningTogether.model.Application;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@Data
public class ApplicationConverter {
    private ModelMapper modelMapper;

    public Application applicationDtoToApplication(ApplicationDTO applicationDTO){
        return modelMapper.map(applicationDTO, Application.class);
    }
}
