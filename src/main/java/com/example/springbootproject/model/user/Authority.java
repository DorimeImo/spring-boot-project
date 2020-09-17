package com.example.springbootproject.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "AUTHORITY")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private AuthorityType type;

    public Authority (AuthorityType type){
        this.type=type;
    }
}
