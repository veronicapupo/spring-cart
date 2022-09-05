package br.com.shopping.cart.controller;

import br.com.shopping.cart.model.Cart;
import br.com.shopping.cart.model.Product;
import br.com.shopping.cart.repository.CartRepository;
import br.com.shopping.cart.repository.ProductRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")

public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(res -> ResponseEntity.ok(res))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Product> putProduct(@Valid @RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        productRepository.deleteById(id);
    }
}
