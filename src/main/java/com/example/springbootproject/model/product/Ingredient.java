package com.example.springbootproject.model.product;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@Table(name="INGREDIENT")
public class Ingredient implements Serializable {

    @Id
    private final String ingredient_id;
    @NotBlank
    private final String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    private final Type type;
    @NotNull
    private final int price;
    @Column(name = "creation_date", columnDefinition = "DATE")
    private LocalDate createdAt;

    @PrePersist
    void placedAt() {
        this.createdAt = LocalDate.now();
    }

    public int getPrice(){
        return price;
    }

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}

