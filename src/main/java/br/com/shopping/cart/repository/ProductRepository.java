package br.com.shopping.cart.repository;

import br.com.shopping.cart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByNameContainingIgnoreCase(@Param("name") String name);
}
