package com.dsumtsov.tacocloud.service.impl;

import com.dsumtsov.tacocloud.config.properties.OrderProperties;
import com.dsumtsov.tacocloud.entity.Order;
import com.dsumtsov.tacocloud.entity.User;
import com.dsumtsov.tacocloud.mapper.OrderMapper;
import com.dsumtsov.tacocloud.repository.jpa.OrderRepository;
import com.dsumtsov.tacocloud.service.OrderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderProperties orderProperties;

    @Override
    public Order save(@NonNull Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(@NonNull Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> findAll() {
        return IterableUtils.toList(orderRepository.findAll());
    }

    public Optional<Order> findById(@NonNull Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findByUser(@NonNull User user) {
        Pageable pageable = PageRequest.of(0, orderProperties.getPageSize());
        return orderRepository.findByUserOrderByPlacedAtDesc(user, pageable);
    }

    @Override
    public Optional<Order> fullUpdate(@NonNull Long id, @NonNull Order order) {

        Optional<Order> optOrder = orderRepository.findById(id);
        if (optOrder.isPresent()) {
            return Optional.of(orderRepository.save(order));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Order> partialUpdate(@NonNull Long id, @NonNull Order order) {

        Optional<Order> optOrder = orderRepository.findById(id);
        if (optOrder.isPresent()) {
            Order existingOrder = optOrder.get();
            OrderMapper.INSTANCE.updateExistingOrder(order, existingOrder);
            return Optional.of(orderRepository.save(existingOrder));
        } else {
            return Optional.empty();
        }
    }
}
