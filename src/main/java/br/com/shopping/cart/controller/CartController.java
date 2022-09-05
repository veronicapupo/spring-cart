package br.com.shopping.cart.controller;


import br.com.shopping.cart.model.Cart;
import br.com.shopping.cart.model.Product;
import br.com.shopping.cart.repository.CartRepository;
import br.com.shopping.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping
    public ResponseEntity<List<Cart>> getAll(){
        return ResponseEntity.ok(cartRepository.findAll());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getById(@PathVariable Long id){
        return cartRepository.findById(id)
                .map(res-> ResponseEntity.ok(res))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Cart> putCart(@Valid @RequestBody Cart cart) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartRepository.save(cart));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        cartRepository.deleteById(id);
    }
}
