package br.com.shopping.cart.repository;

import br.com.shopping.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

  //  public List<Product> findByIdContainingIgnoreCase(@Param("id") Long, );
}
