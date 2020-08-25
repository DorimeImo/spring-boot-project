package com.example.springbootproject.repository;

import com.example.springbootproject.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;


public interface OrderRepository extends CrudRepository<Order, Long> {
}
