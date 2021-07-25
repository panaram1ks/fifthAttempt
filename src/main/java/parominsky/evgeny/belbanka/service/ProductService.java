package parominsky.evgeny.belbanka.service;



import parominsky.evgeny.belbanka.entity.Metal;
import parominsky.evgeny.belbanka.entity.Product;
import parominsky.evgeny.belbanka.repository.ProductRepository;

import java.util.List;


public interface ProductService extends Service<Integer, Product, ProductRepository>{

    List<Product> getProductList(Metal metal);
}
