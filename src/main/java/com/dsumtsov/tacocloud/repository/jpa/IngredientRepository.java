package com.dsumtsov.tacocloud.repository.jpa;

import com.dsumtsov.tacocloud.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
