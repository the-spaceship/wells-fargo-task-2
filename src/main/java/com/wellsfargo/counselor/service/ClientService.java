package com.wellsfargo.counselor.service;

import com.wellsfargo.counselor.payload.ClientDto;
import org.springframework.stereotype.Service;


public interface ClientService {

    ClientDto createClient(ClientDto clientDto, long advisorId);

    void deleteClient(long clientId);


    ClientDto updateClient(long clientId, ClientDto clientDto, long advisorId);
}
