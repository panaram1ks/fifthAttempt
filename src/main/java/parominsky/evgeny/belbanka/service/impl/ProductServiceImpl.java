package parominsky.evgeny.belbanka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import parominsky.evgeny.belbanka.entity.Metal;
import parominsky.evgeny.belbanka.entity.Product;
import parominsky.evgeny.belbanka.repository.ProductRepository;
import parominsky.evgeny.belbanka.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductRepository getRepository() {
        return productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductList(Metal metal) {
        return this.productRepository.findAll();
    }
}
