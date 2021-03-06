package com.example.springbootproject.model.product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.springbootproject.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="TACO")
public class Taco implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "taco_id")
    private Long id;

    @NotBlank
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @Column(name = "creation_date", columnDefinition = "DATE")
    private LocalDate createdAt;

    @Column(name="taco_price")
    private int price;

    @Column(name="taco_rating")
    private int rating;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private User creator;


    @NotNull
    @Size(min=2, message="You must choose at least 2 ingredient")
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;

    public Taco(String name){
        this.name=name;
    }

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDate.now();
    }

    public void increaseRating(){
        rating+=1;
    }

    public int getPrice(){
        price=0;
        if(ingredients.size()>0){
            for (Ingredient i: ingredients){
                price+=i.getPrice();
            }
        }
        return this.price;
    }

    @Override
    public String toString() {
        return "Taco{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", rating=" + rating +
                ", creator=" + creator +
                ", ingredients=" + ingredients +
                '}';
    }
}