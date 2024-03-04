package org.tolga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tolga.repository.entity.Order;
import org.tolga.repository.entity.Product;

import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {

    Optional<Order> findByCustomerId(Long customerId);

    boolean existsByCustomerId(Long customerId);

}
