package com.dsumtsov.tacocloud.repository.jdbc;

import com.dsumtsov.tacocloud.domain.Taco;

public interface TacoRepository {

    Long save(Long orderId, int orderKey, Taco taco);
}
