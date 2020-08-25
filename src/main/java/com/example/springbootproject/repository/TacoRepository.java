package com.example.springbootproject.repository;

import com.example.springbootproject.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
