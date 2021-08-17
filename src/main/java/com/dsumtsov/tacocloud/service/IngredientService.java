package com.dsumtsov.tacocloud.service;

import com.dsumtsov.tacocloud.model.Ingredient;

public interface IngredientService {

    Iterable<Ingredient> findAll();
}
