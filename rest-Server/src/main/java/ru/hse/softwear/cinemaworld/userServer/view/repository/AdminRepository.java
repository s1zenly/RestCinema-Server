package ru.hse.softwear.cinemaworld.userServer.view.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hse.softwear.cinemaworld.userServer.view.entity.Admin;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    @Query("SELECT * FROM admins WHERE email = :email")
    Optional<Admin> findByEmail(String email);
}
