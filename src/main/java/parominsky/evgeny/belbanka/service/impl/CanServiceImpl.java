package parominsky.evgeny.belbanka.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import parominsky.evgeny.belbanka.repository.CanRepository;
import parominsky.evgeny.belbanka.service.CanService;

@Service
@Transactional
public class CanServiceImpl implements CanService {

   private CanRepository canRepository;

    @Autowired
    private void setCanRepository(CanRepository canRepository){
        this.canRepository = canRepository;
    }

    @Override
    public CanRepository getRepository() {
        return canRepository;
    }
}
