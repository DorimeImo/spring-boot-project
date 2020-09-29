package com.example.springbootproject.model.user;


import com.example.springbootproject.model.product.Order;
import com.example.springbootproject.model.product.Taco;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name="USER")
public class User{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer user_id;

    @NotBlank
    @Column(nullable = false, unique = true)
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") })
    private Set<Authority> authorities = new HashSet<>();

    @Column(name = "creation_date", columnDefinition = "DATE")
    private LocalDate createdAt;

    @OneToMany(mappedBy="creator")
    private List<Taco> customTacos;

    @OneToMany(mappedBy="creator")
    private List <Order> orderHistory;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="address_id")
    private Address address;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="payment_id")
    private Payment payment;

    public User(@NotBlank @Size(min = 3, message = "Name must be at least 3 characters long") String name,
                @NotBlank String password, @NotBlank @Email(message = "Email should be valid") String email,
                @NotNull Set<Authority> authorities) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    @PrePersist
    private void createdAt(){
        createdAt = LocalDate.now();
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
