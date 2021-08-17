package com.dsumtsov.tacocloud.api.controller;

import com.dsumtsov.tacocloud.api.assembler.TacoModelAssembler;
import com.dsumtsov.tacocloud.api.model.TacoModel;
import com.dsumtsov.tacocloud.model.Taco;
import com.dsumtsov.tacocloud.service.TacoService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/design",
        produces = APPLICATION_JSON_VALUE) // handle requests with 'Accept' header includes 'application/json'
@RequiredArgsConstructor
public class DesignTacoApiController {

    private static final TacoModelAssembler tacoModelAssembler =
            new TacoModelAssembler();

    private final TacoService tacoService;

    @GetMapping("/recent")
    public ResponseEntity<CollectionModel<TacoModel>> recentTacos() {

        List<Taco> tacos = tacoService.findAll();

        CollectionModel<TacoModel> recentModels = tacoModelAssembler.toCollectionModel(tacos);
        recentModels.add(
                linkTo(methodOn(DesignTacoApiController.class).recentTacos())
                        .withRel("recents"));

        return new ResponseEntity<>(recentModels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacoModel> getTacoById(@PathVariable("id") Long id) {

        Optional<Taco> optTaco = tacoService.findById(id);

        return optTaco
                .map(taco -> {
                    TacoModel model = tacoModelAssembler.toModel(taco);
                    model.add(
                            linkTo(methodOn(DesignTacoApiController.class).recentTacos())
                                    .withRel("recents"));
                    return model;
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE) // handle requests whose 'Content-type' matches 'application/json'
    public ResponseEntity<TacoModel> postTaco(@RequestBody Taco taco) {

        Taco saved = tacoService.save(taco);
        TacoModel model = tacoModelAssembler.toModel(saved);
        model.add(
                linkTo(methodOn(DesignTacoApiController.class).recentTacos())
                        .withRel("recents"));

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }
}
