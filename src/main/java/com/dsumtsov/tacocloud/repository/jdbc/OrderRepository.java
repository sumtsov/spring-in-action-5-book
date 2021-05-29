package com.dsumtsov.tacocloud.repository.jdbc;

import com.dsumtsov.tacocloud.domain.Order;

public interface OrderRepository {

    Order save(Order order);
}
