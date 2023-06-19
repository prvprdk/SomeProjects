package org.example.service;

import org.example.domain.Client;
import org.example.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ClientService {
    @Autowired
    ClientRepo clientRepo;


    public void add(Client client) {
        clientRepo.save(client);
    }

    public void update(Client client) {
        clientRepo.save(client);
    }
}
