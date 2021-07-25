package parominsky.evgeny.belbanka.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import parominsky.evgeny.belbanka.entity.Cover;

public interface CoverRepository extends JpaRepository<Cover, Integer> {

}