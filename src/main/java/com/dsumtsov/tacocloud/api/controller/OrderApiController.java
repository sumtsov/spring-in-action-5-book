package com.dsumtsov.tacocloud.api.controller;

import com.dsumtsov.tacocloud.entity.Order;
import com.dsumtsov.tacocloud.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path = "/api/v1/orders",
                produces = APPLICATION_JSON_VALUE) // handle requests with 'Accept' header includes 'application/json'
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {

        List<Order> orders = orderService.findAll();

        return CollectionUtils.isNotEmpty(orders)
                ? new ResponseEntity<>(orders, HttpStatus.OK)
                : new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> postOrder(@RequestBody Order order) {

        Order saved =  orderService.save(order);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}",
                consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> putOrder(@PathVariable("id") Long id,
                                          @RequestBody Order order) {

        Optional<Order> optOrder = orderService.fullUpdate(id, order);

        return optOrder
                .map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/{id}",
                  consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> patchOrder(@PathVariable("id") Long id,
                                            @RequestBody Order order) {

        Optional<Order> optOrder = orderService.partialUpdate(id, order);

        return optOrder
                .map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable("id") Long id) {

        try {
            orderService.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
