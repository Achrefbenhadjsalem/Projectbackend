package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Payment;
import com.test.COCONSULT.Entity.Prospect;
import com.test.COCONSULT.Interfaces.ProspectService;
import com.test.COCONSULT.Reposotories.ProspectRepository;
import com.test.COCONSULT.Services.ExcelProspectUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProspectImpl implements ProspectService {
    @Autowired
    ProspectRepository prospectRepository ;
    @Override
    public List<Prospect> retrieveProspect() {
        return prospectRepository.findAll() ;
    }


    @Override
    public Prospect updateProspect(Prospect prospect) {
        return prospectRepository.save(prospect);
    }


    @Override
    public Prospect addProspect(Prospect prospect) {
        return prospectRepository.save(prospect);
    }



    @Override
    public Prospect retrieveProspect(Long idProspect) {
        Optional<Prospect> ProspectOptional = prospectRepository.findById(idProspect);
        return ProspectOptional.orElse(null);
    }

    @Override
    public void removeProspect(Long idProspect) {
        prospectRepository.deleteById(idProspect);
    }

    @Override
    public void saveProspectsToDatabase(MultipartFile file){
        if(ExcelProspectUploadService.isValidExcelFile(file)){
            try {
                List<Prospect> prospects = ExcelProspectUploadService.getProspectsDataFromExcel(file.getInputStream());
                this.prospectRepository.saveAll(prospects);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }


}
