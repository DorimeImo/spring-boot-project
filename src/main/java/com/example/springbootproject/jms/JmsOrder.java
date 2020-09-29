package com.example.springbootproject.jms;

import com.example.springbootproject.model.product.Taco;
import com.example.springbootproject.model.user.Address;

import java.util.ArrayList;
import java.util.List;

public class JmsOrder {

    private Address address;
    private List<Taco> tacos;


    public void setTacos(List<Taco> tacos) {
        this.tacos = tacos;
    }

    public List<Taco> getTacos() {
        return tacos;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "JmsOrder{" +
                "address=" + address +
                ", tacos=" + tacos +
                '}';
    }
}
