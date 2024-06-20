package com.innovix.service;

import com.innovix.entity.ShoppingCart;
import com.innovix.entity.Person;
import com.innovix.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public List<ShoppingCart> listAll() {
        return shoppingCartRepository.findAll();
    }

    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart findById(Long id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }

    public List<ShoppingCart> findByCustomer(Person customer) {
        return shoppingCartRepository.findByCustomer(customer);
    }

    public void delete(Long id) {
        shoppingCartRepository.deleteById(id);
    }
}
