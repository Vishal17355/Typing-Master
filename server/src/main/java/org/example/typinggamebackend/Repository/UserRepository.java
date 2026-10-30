package org.example.typinggamebackend.Repository;

import java.util.Optional;

import org.example.typinggamebackend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
