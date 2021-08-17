package com.dsumtsov.tacocloud.web.controller;

import com.dsumtsov.tacocloud.model.Ingredient;
import com.dsumtsov.tacocloud.model.Order;
import com.dsumtsov.tacocloud.model.Taco;
import com.dsumtsov.tacocloud.enums.IngredientType;
import com.dsumtsov.tacocloud.service.IngredientService;
import com.dsumtsov.tacocloud.service.TacoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order") // defines object that should be kept in session and available across multiple requests
@RequiredArgsConstructor
public class DesignTacoController {

    private final IngredientService ingredientService;
    private final TacoService tacoService;

    @ModelAttribute(name = "order") // invoked when request is handled, fills Model with data
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientService.findAll().forEach(ingredients::add);

        for (IngredientType type : IngredientType.values()) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        return "design";
    }

    @PostMapping
    public String processTacoDesign(
            @Valid @ModelAttribute("taco") Taco taco,
            @ModelAttribute Order order,
            Errors errors) {

        if (errors.hasErrors()) {
            return "design";
        }

        log.info("Processing taco " + taco);
        Taco saved = tacoService.save(taco);
        order.addTaco(saved);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, IngredientType type) {

        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
