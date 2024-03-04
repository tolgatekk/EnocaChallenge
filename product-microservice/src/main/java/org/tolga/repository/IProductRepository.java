package org.tolga.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tolga.repository.entity.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {

   Boolean existsByProductName(String productName);


}
