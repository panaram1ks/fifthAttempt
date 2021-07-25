package parominsky.evgeny.belbanka.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import parominsky.evgeny.belbanka.repository.CoverRepository;
import parominsky.evgeny.belbanka.service.CoverService;

@Service
@Transactional
public class CoverServiceImpl implements CoverService {

    @Autowired
    private CoverRepository coverRepository;

    @Override
    public CoverRepository getRepository() {
        return coverRepository;
    }
}
