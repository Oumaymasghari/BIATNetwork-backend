package tn.esprit.biat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.biat.Entity.ERole;

import tn.esprit.biat.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :role")
  List<User> findByRole(@Param("role") ERole role);

  @Query("SELECT u FROM User u WHERE u.username = ?1")
  User getUserByUsername(String username);
}
