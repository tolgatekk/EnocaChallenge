package org.tolga.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tolga.repository.entity.Auth;


import java.util.Optional;


@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {

    Boolean existsByEmail(String email);  //Parametre olarak girilen emailin veritabanında olup olmadığını kontrol ediyoruz.
    Boolean existsByPhoneNumber(String phoneNumber); //Parametre olarak girilen telefon numarasının veritabanında olup olmadığını kontrol ediyoruz.
    Optional<Auth> findOptionalByEmailAndPassword(String email, String password); //Parametre olarak girilen email ve password'ün veritabanında olup olmadığını kontrol ediyoruz ve optional olarak geriye dönüyoruz.
    Optional<Auth> findOptionalByEmail(String email); //Parametre olarak girilen email'in veritabanında olup olmadığını kontrol ediyoruz ve optional olarak geriye dönüyoruz.




    Optional<Auth> findOptionalById(Long authid);


}
