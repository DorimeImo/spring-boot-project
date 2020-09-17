package com.example.springbootproject.repository;

import com.example.springbootproject.model.product.Taco;
import com.example.springbootproject.model.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {

    List<Taco> getAllByCreator(User creator);

    Optional<Taco> findById(Long id);


}
