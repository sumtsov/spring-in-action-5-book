package com.dsumtsov.tacocloud.api.assembler;

import com.dsumtsov.tacocloud.api.controller.IngredientApiController;
import com.dsumtsov.tacocloud.api.model.IngredientModel;
import com.dsumtsov.tacocloud.entity.Ingredient;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class IngredientModelAssembler
        extends RepresentationModelAssemblerSupport<Ingredient, IngredientModel> {

    public IngredientModelAssembler() {
        super(IngredientApiController.class, IngredientModel.class);
    }

    protected IngredientModel instantiateModel(Ingredient ingredient) {
        return new IngredientModel(ingredient);
    }

    public IngredientModel toModel(Ingredient ingredient) {
        return createModelWithId(ingredient.getId(), ingredient);
    }
}
