package parominsky.evgeny.belbanka.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import parominsky.evgeny.belbanka.entity.Metal;

public interface MetalRepository extends JpaRepository<Metal, Integer> {

    //Metal findMetal(String name, LocalDate localDate);
}