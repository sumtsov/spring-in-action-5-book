package com.dsumtsov.tacocloud.service;

import com.dsumtsov.tacocloud.entity.Taco;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface TacoService {

    Optional<Taco> findById(@NonNull Long id);
    Taco save(@NonNull Taco taco);
    List<Taco> findAll();
}
