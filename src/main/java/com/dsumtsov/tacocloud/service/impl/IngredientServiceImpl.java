package com.dsumtsov.tacocloud.service.impl;

import com.dsumtsov.tacocloud.domain.Ingredient;
import com.dsumtsov.tacocloud.repository.jpa.IngredientRepository;
import com.dsumtsov.tacocloud.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public Iterable<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }
}
