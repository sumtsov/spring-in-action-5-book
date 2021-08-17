package com.dsumtsov.tacocloud.repository.jdbc;

import com.dsumtsov.tacocloud.model.Order;

public interface OrderRepository {

    Order save(Order order);
}
