package com.example.springbootproject.model.product;

import javax.persistence.*;

import com.example.springbootproject.model.user.User;
import lombok.*;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor()
@Table(name="USER_ORDER")
public class Order {

    @Id
    @GeneratedValue
    private Long order_id;

    private int totalPrice;

    @Column(name = "creation_date", columnDefinition = "DATE")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User creator;

    @ManyToMany
    @Size(min=1, message="You must choose at least 1 taco")
    private List<Taco> tacos;

    public int calculateTotalPrice(){
        this.totalPrice = 0;
        if(tacos.size()!=0){
            for(Taco i: tacos){
                totalPrice+=i.getPrice();
            }
            return totalPrice;
        }
        return totalPrice;
    }

    public void addTaco(Taco taco) {
        if(tacos==null)
            tacos=new ArrayList<>();
        this.tacos.add(taco);
    }

    @PrePersist
    void placedAt() {
        this.createdAt = LocalDate.now();
        increaseRating();
    }

    private void increaseRating(){
        for(Taco taco: tacos){
            taco.increaseRating();
        }
    }


}
