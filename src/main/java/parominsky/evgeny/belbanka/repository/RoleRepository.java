package parominsky.evgeny.belbanka.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import parominsky.evgeny.belbanka.entity.Role;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findRoleByName(String name);

    List<Role> findByNameIn(Collection<String> names);
}