package ru.hse.softwear.cinemaworld.restServer.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hse.softwear.cinemaworld.restServer.view.entity.Admin;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEmail(String email);
}
