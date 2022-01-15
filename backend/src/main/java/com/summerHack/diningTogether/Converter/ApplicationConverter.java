package com.summerHack.diningTogether.Converter;

import com.summerHack.diningTogether.DTO.ApplicationDTO;
import com.summerHack.diningTogether.model.Application;

public abstract class ApplicationConverter {
    public abstract Application applicationDtoToApplication(ApplicationDTO applicationDTO);
}
