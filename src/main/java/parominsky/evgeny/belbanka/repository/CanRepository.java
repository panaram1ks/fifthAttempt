package parominsky.evgeny.belbanka.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import parominsky.evgeny.belbanka.entity.Can;

public interface CanRepository extends JpaRepository<Can, Integer> {
}
