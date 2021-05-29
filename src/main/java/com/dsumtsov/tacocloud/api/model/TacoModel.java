package com.dsumtsov.tacocloud.api.model;

import com.dsumtsov.tacocloud.api.assembler.IngredientModelAssembler;
import com.dsumtsov.tacocloud.domain.Taco;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;

@Getter
@Relation(value = "taco", collectionRelation = "tacos")
public class TacoModel extends RepresentationModel<TacoModel> {

    private static final IngredientModelAssembler ingredientModelAssembler =
            new IngredientModelAssembler();

    private String name;
    private Date createdAt;
    private CollectionModel<IngredientModel> ingredients;

    public TacoModel(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = ingredientModelAssembler.toCollectionModel(taco.getIngredients());
    }
}
