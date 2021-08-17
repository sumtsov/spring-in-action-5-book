package com.dsumtsov.tacocloud.service;

import com.dsumtsov.tacocloud.model.Order;
import com.dsumtsov.tacocloud.model.User;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();
    Order save(@NonNull Order order);
    void deleteById(@NonNull  Long id);
    List<Order> findByUser(@NonNull User user);
    Optional<Order> findById(@NonNull Long id);
    Optional<Order> fullUpdate(@NonNull Long id, @NonNull Order order);
    Optional<Order> partialUpdate(@NonNull Long id, @NonNull Order order);
}
