package com.dsumtsov.tacocloud.api.model;

import com.dsumtsov.tacocloud.model.Ingredient;
import com.dsumtsov.tacocloud.enums.IngredientType;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(value = "ingredient", collectionRelation = "ingredients")
public class IngredientModel extends RepresentationModel<IngredientModel> {

    private String name;
    private IngredientType type;

    public IngredientModel(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }
}
