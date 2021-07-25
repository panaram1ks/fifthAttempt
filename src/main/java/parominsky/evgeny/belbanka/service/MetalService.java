package parominsky.evgeny.belbanka.service;

import parominsky.evgeny.belbanka.entity.Metal;
import parominsky.evgeny.belbanka.repository.MetalRepository;

import java.util.List;

public interface MetalService extends Service<Integer, Metal, MetalRepository> {

    List<Metal> getAllMetals();


}
