package com.dsumtsov.tacocloud.service;

import com.dsumtsov.tacocloud.entity.Ingredient;

public interface IngredientService {

    Iterable<Ingredient> findAll();
}
