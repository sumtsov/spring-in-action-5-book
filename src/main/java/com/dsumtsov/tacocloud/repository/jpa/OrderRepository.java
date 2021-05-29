package com.dsumtsov.tacocloud.repository.jpa;

import com.dsumtsov.tacocloud.domain.Order;
import com.dsumtsov.tacocloud.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
