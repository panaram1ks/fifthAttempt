package parominsky.evgeny.belbanka.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import parominsky.evgeny.belbanka.entity.Metal;
import parominsky.evgeny.belbanka.repository.MetalRepository;
import parominsky.evgeny.belbanka.repository.ProductRepository;
import parominsky.evgeny.belbanka.service.MetalService;

import java.util.List;

@Service
@Transactional
public class MetalServiceImpl implements MetalService {

    @Autowired
    private MetalRepository metalRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public MetalRepository getRepository() {
        return metalRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Metal> getAllMetals() {
        return metalRepository.findAll();
    }




}
