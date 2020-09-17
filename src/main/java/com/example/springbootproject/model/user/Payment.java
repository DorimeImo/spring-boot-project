package com.example.springbootproject.model.user;

import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor()
@Table(name="PAYMENT")
public class Payment {

    @Id
    @GeneratedValue
    private Integer payment_id;

    @NotBlank
    @Size(min=1, message = "Name is required.")
    private String name;

    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;
}
