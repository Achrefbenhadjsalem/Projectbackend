package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.ActivitySalesTeam;
import com.test.COCONSULT.Entity.Contract;
import com.test.COCONSULT.Entity.Repertoire;

import java.util.List;

public interface ContractService {
    List<Contract> retrieveContracts();
    Contract updateContract(Contract contract);
    Contract addContract(Contract contract) ;
    Contract addContractAffectRep(Contract contract, Long repertoireId) ;
    Contract retrieveContract(Long idContract);
    Contract updateContractAffectRep(Contract updatedContract, Long contractId, Long repertoireId) ;

    public String getRepertoireContactByContractId(Long idContract) ;

    public List<Contract> retrieveContractsWithRepertoireContact() ;
    void removeContract(Long idContract);
}
