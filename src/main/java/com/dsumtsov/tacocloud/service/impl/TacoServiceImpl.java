package com.dsumtsov.tacocloud.service.impl;

import com.dsumtsov.tacocloud.entity.Taco;
import com.dsumtsov.tacocloud.repository.jpa.TacoRepository;
import com.dsumtsov.tacocloud.service.TacoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TacoServiceImpl implements TacoService {

    private final TacoRepository tacoRepository;

    @Override
    public List<Taco> findAll() {
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageRequest).getContent();
    }

    @Override
    public Optional<Taco> findById(@NonNull Long id) {
        return tacoRepository.findById(id);
    }

    @Override
    public Taco save(Taco taco) {
        return tacoRepository.save(taco);
    }
}
