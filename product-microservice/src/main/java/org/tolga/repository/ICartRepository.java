package org.tolga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tolga.repository.entity.Cart;

import java.util.Optional;

@Repository
public interface ICartRepository extends JpaRepository<Cart,Long> {

    Optional<Cart> findByCustomerId(Long customerId);
}
