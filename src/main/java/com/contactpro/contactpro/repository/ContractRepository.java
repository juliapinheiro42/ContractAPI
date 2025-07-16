package com.contactpro.contactpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactpro.contactpro.model.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findByStatus(String status);

    List<Contract> findByClientId(Long clientId);
}
