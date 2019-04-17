package br.com.rnascimento.api.utils;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class ModelMapperUtil {
	
	private static ModelMapper modelMapper;
	
	@Bean
	public static ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static <T> List<T> converter(List<?> lista, Class<T> classe) {
        modelMapper = modelMapper();
        List<T> newList = new ArrayList<>();
        for (Object object : lista) {
            newList.add(modelMapper.map(object, classe));
        }
        return newList;
    }

	public static <T> T converter(Object object, Class<T> classe) {
		modelMapper = modelMapper();
        if(object == null) {
            return null;
        }
        return modelMapper.map(object, classe);
    }

}