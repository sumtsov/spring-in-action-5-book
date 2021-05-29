package com.dsumtsov.tacocloud.service;

import com.dsumtsov.tacocloud.domain.Ingredient;

public interface IngredientService {

    Iterable<Ingredient> findAll();
}
