package com.contactpro.contactpro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contactpro.contactpro.model.Client;
import com.contactpro.contactpro.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public Client update(Long id, Client updatedClient) {
        return clientRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedClient.getName());
                    existing.setDocument(updatedClient.getDocument());
                    existing.setEmail(updatedClient.getEmail());
                    existing.setTelephone(updatedClient.getTelephone());
                    return clientRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        clientRepository.deleteById(id);
    }
}
