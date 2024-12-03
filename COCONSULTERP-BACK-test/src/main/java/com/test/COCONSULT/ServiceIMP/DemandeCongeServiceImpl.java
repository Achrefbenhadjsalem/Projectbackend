package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.DemandeConge;
import com.test.COCONSULT.Interfaces.DemandeCongeService;
import com.test.COCONSULT.Reposotories.DemandeCongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandeCongeServiceImpl implements DemandeCongeService {

    @Autowired
    DemandeCongeRepository demandeCongeRepository;
    @Override
    public DemandeConge addDemandeConge(DemandeConge demandeConge) {
        return demandeCongeRepository.save(demandeConge);
    }
}
