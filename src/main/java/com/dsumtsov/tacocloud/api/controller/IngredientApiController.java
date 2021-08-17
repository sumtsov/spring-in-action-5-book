package com.dsumtsov.tacocloud.api.controller;

import com.dsumtsov.tacocloud.model.Ingredient;
import com.dsumtsov.tacocloud.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path = "/api/v1/ingredients",
        produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class IngredientApiController {

    private final IngredientService ingredientService;

    @GetMapping
    public Iterable<Ingredient> allIngredients() {
        return ingredientService.findAll();
    }
}
