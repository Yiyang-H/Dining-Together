package com.summerHack.diningTogether.Converter;

import com.summerHack.diningTogether.DTO.ApplicationDTO;
import com.summerHack.diningTogether.model.Application;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public abstract class ApplicationConverter {
    private ModelMapper modelMapper;

    public abstract Application applicationDtoToApplication(ApplicationDTO applicationDTO);
}
