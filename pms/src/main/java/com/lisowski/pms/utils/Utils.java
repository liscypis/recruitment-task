package com.lisowski.pms.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class Utils {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }



}
