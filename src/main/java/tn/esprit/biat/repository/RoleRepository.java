package tn.esprit.biat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biat.Entity.Personne;
import tn.esprit.biat.Entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
