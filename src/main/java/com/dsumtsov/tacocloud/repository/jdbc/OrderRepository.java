package com.dsumtsov.tacocloud.repository.jdbc;

import com.dsumtsov.tacocloud.entity.Order;

public interface OrderRepository {

    Order save(Order order);
}
