package com.contactpro.contactpro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contactpro.contactpro.model.Contract;
import com.contactpro.contactpro.repository.ContractRepository;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    public Optional<Contract> findById(Long id) {
        return contractRepository.findById(id);
    }

    public Contract create(Contract contract) {
        return contractRepository.save(contract);
    }

    public Contract update(Long id, Contract updatedContract) {
        return contractRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedContract.getTitle());
                    existing.setDataStart(updatedContract.getDataStart());
                    existing.setDataEnd(updatedContract.getDataEnd());
                    existing.setStatus(updatedContract.getStatus());
                    existing.setClient(updatedContract.getClient());
                    existing.setAttachments(updatedContract.getAttachments());
                    return contractRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));
    }

    public void delete(Long id) {
        contractRepository.deleteById(id);
    }
}
