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
	
	/**
	 * 
	 * Inicia o modelMapper
	 * 
	 */
	@Bean
	public static void modelMapper() {
		modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        modelMapper.getConfiguration().setFullTypeMatchingRequired(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	/**
	 * Converte uma lista de um tipo para o outro
	 * 
	 * @param <T>
	 * @param lista
	 * @param classe
	 * @return
	 */
	public static <T> List<T> converter(List<?> lista, Class<T> classe) {
        List<T> newList = new ArrayList<>();
        for (Object object : lista) {
            newList.add(modelMapper.map(object, classe));
        }
        return newList;
    }

	/**
	 * 
	 * Converte um objeto de um tipo para o outro
	 * 
	 * @param <T>
	 * @param object
	 * @param classe
	 * @return
	 */
	public static <T> T converter(Object object, Class<T> classe) {
        if(object == null) {
            return null;
        }
        return modelMapper.map(object, classe);
    }

}