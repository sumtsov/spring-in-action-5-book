package com.dsumtsov.tacocloud.repository.jpa;

import com.dsumtsov.tacocloud.entity.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
