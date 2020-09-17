package com.example.springbootproject.mapper;

import com.example.springbootproject.model.product.Taco;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TacoMapper {
    @Select("SELECT * FROM TACO WHERE taco_price = #{price}")
    List<Taco> getArticle(@Param("price") int price);
}
