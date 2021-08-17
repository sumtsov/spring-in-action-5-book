package com.dsumtsov.tacocloud.model;

import com.dsumtsov.tacocloud.enums.IngredientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force=true) // force = true sets final fields to null
public class Ingredient {

    @Id
    private final String id;
    private final String name;
    private final IngredientType type;
}
