package parominsky.evgeny.belbanka.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import parominsky.evgeny.belbanka.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}