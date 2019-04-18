package br.com.rnascimento.api.utils;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;

public class ModelMapperUtil {
	
	private static ModelMapper modelMapper;
	
	static {
		modelMapper();
    }
	
	@Bean
	public static void modelMapper() {
		modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        modelMapper.getConfiguration().setFullTypeMatchingRequired(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	public static <T> List<T> converter(List<?> lista, Class<T> classe) {
        List<T> newList = new ArrayList<>();
        for (Object object : lista) {
            newList.add(modelMapper.map(object, classe));
        }
        return newList;
    }

	public static <T> T converter(Object object, Class<T> classe) {
        if(object == null) {
            return null;
        }
        return modelMapper.map(object, classe);
    }

}