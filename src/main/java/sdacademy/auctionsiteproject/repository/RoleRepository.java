package sdacademy.auctionsiteproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sdacademy.auctionsiteproject.entity.Roles;

import javax.management.relation.Role;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);
}
