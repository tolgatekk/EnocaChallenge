package org.tolga.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tolga.repository.entity.Customer;

import java.util.Optional;


@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {

    Boolean existsByEmail(String email);
    Boolean existsByPhoneNumber(String phoneNumber); //Parametre olarak girilen telefon numarasının veritabanında olup olmadığını kontrol ediyoruz.
   Optional<Customer> findOptionalByEmail(String email); //Parametre olarak girilen email'in veritabanında olup olmadığını kontrol ediyoruz ve optional olarak geriye dönüyoruz.

    //Parametre olarak girilen id değerinin veritabanında olup olmadığını kontrol edecek ve optional olarak geriye dönecek metot:
    Optional<Customer> findOptionalById(Long authid);


}
