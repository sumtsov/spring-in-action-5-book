package com.dsumtsov.tacocloud.web.converter;

import com.dsumtsov.tacocloud.entity.Ingredient;
import com.dsumtsov.tacocloud.repository.jpa.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * see https://www.baeldung.com/spring-type-conversions
 */
@Component
@RequiredArgsConstructor
public class IdToIngredientConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }
}
