package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Entity.Repertoire;
import com.test.COCONSULT.Interfaces.ContractService;
import com.test.COCONSULT.Reposotories.ContractRepository;
import com.test.COCONSULT.Reposotories.RepertoireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private RepertoireRepository repertoireRepository;

    @Override
    public List<Contract> retrieveContracts() {
        return contractRepository.findAll();
    }

    @Override
    public Contract updateContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract addContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract addContractAffectRep(Contract contract, Long repertoireId) {
        Repertoire repertoire = repertoireRepository.findById(repertoireId).orElse(null);
        List<Repertoire> myrepo=new ArrayList<>();

        if (repertoire != null) {
            myrepo.add(repertoire);
            contract.setRepertoires(myrepo);
            // contract.getRepertoires().add(repertoire); // Add the repertoire to the list of repertoires associated with the contract
            // Automatically set repertoireContact to the Contact attribute of Repertoire
            contract.setRepertoireContact(repertoire.getContact());

            // Generate a random reference number
            String reference = generateRandomReference();
            contract.setReferenceContract(reference);
            return contractRepository.save(contract);
        } else {
            return null;
        }
    }

    // Method to generate a random reference number
    private String generateRandomReference() {
        StringBuilder sb = new StringBuilder("REF");
        Random random = new Random();
        // Generate 6 random digits
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10)); // Append a random digit (0-9)
        }
        return sb.toString();
    }


    @Override
    public Contract updateContractAffectRep(Contract updatedContract, Long contractId, Long repertoireId) {
        Optional<Contract> optionalContract = contractRepository.findById(contractId);

        if (optionalContract.isPresent()) {
            Contract existingContract = optionalContract.get();
            // Update contract details
            existingContract.setDescription(updatedContract.getDescription());
            existingContract.setDateContract(updatedContract.getDateContract());
            // Update other fields as needed

            // Retrieve the repertoire
            Repertoire repertoire = repertoireRepository.findById(repertoireId).orElse(null);
            if (repertoire != null) {
                existingContract.getRepertoires().add(repertoire);
            }
            return contractRepository.save(existingContract);
        } else {
            return null;
        }
    }


    @Override
    public List<Contract> retrieveContractsWithRepertoireContact() {
        List<Contract> contracts = contractRepository.findAll();

        for (Contract contract : contracts) {
            // Populate repertoireContact for each contract
            StringBuilder sb = new StringBuilder();
            for (Repertoire repertoire : contract.getRepertoires()) {
                sb.append(repertoire.getContact()).append(", ");
            }
            String repertoireContact = sb.toString();
            contract.setRepertoireContact(repertoireContact);
        }

        return contracts;
    }

    @Override
    public Contract retrieveContract(Long idContract) {
        Optional<Contract> contractOptional = contractRepository.findById(idContract);
        return contractOptional.orElse(null);
    }

    @Override
    public String getRepertoireContactByContractId(Long idContract) {
        Optional<Contract> optionalContract = contractRepository.findById(idContract);
        if (optionalContract.isPresent()) {
            Contract contract = optionalContract.get();
            StringBuilder sb = new StringBuilder();
            for (Repertoire repertoire : contract.getRepertoires()) {
                sb.append(repertoire.getContact()).append(", ");
            }
            return sb.toString();
        } else {
            return null;
        }
    }

    @Override
    public void removeContract(Long idContract) {
        Optional<Contract> optionalContract = contractRepository.findById(idContract);
        Contract contract = optionalContract.get();
        contract.setRepertoires(null);
        contractRepository.save(contract);

        contractRepository.deleteById(idContract);

    }

}