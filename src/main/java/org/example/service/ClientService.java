package org.example.service;

import org.example.domain.Client;
import org.example.repository.ClientRepo;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service

public class ClientService {

    private final ClientRepo clientRepo;

    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }


    public void delete(Client client) {
        clientRepo.delete(client);
    }

    public void add(Client client) {
        clientRepo.save(client);
    }

    public void update(Client clientFromDb, Client clientUpdate) {

        if (clientUpdate.getContractSet() != null) {
            clientFromDb.getContractSet().clear();
            clientFromDb.setContractSet(clientUpdate.getContractSet());
        }
        if (!StringUtils.isEmpty(clientUpdate.getName())) {
            clientFromDb.setName(clientUpdate.getName());
        }
        if (!StringUtils.isEmpty(clientUpdate.getCompany())) {
            clientFromDb.setCompany(clientUpdate.getCompany());
        }
        if (!StringUtils.isEmpty(clientUpdate.getEmail())) {
            clientFromDb.setEmail(clientUpdate.getEmail());
        }
        if (!StringUtils.isEmpty(clientUpdate.getPhone())) {
            clientFromDb.setPhone(clientUpdate.getPhone());
        }
        if (!StringUtils.isEmpty(clientUpdate.getSite())) {
            clientFromDb.setSite(clientUpdate.getSite());
        }

        clientRepo.save(clientFromDb);
    }


}
