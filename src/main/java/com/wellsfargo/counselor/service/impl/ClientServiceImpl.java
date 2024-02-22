package com.wellsfargo.counselor.service.impl;

import com.wellsfargo.counselor.entity.Advisor;
import com.wellsfargo.counselor.entity.Client;
import com.wellsfargo.counselor.exception.ResourceNotFoundException;
import com.wellsfargo.counselor.payload.ClientDto;
import com.wellsfargo.counselor.repository.AdvisorRepository;
import com.wellsfargo.counselor.repository.ClientRepository;
import com.wellsfargo.counselor.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@Service
public class ClientServiceImpl implements ClientService {
    private AdvisorRepository advisorRepository;
    private ClientRepository clientRepository;
    private ModelMapper modelMapper;

    public ClientServiceImpl(AdvisorRepository advisorRepository, ClientRepository clientRepository, ModelMapper modelMapper) {
        this.advisorRepository = advisorRepository;
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ClientDto createClient(ClientDto clientDto, long advisorId) {
        Advisor advisor = advisorRepository.findById(advisorId).orElseThrow(() -> new ResourceNotFoundException("Advisor not found with id:" + advisorId));
        Client client = new Client();
        client.setFirst_name(clientDto.getFirst_name());
        client.setLast_name(clientDto.getLast_name());
        client.setAddress(clientDto.getAddress());
        client.setPhone(clientDto.getPhone());
        client.setEmail(clientDto.getEmail());
        client.setAdvisor(advisor);

        Client savedClient = clientRepository.save(client);

        ClientDto dto = new ClientDto();
        dto.setClient_id(savedClient.getClient_id());
        dto.setFirst_name(savedClient.getFirst_name());
        dto.setLast_name(savedClient.getLast_name());
        dto.setAddress(savedClient.getAddress());
        dto.setEmail(savedClient.getEmail());
        dto.setPhone(savedClient.getPhone());
        return dto;

    }

    @Override
    public void deleteClient(long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public ClientDto updateClient(long clientId, ClientDto clientDto, long advisorId) {
        Advisor advisor = advisorRepository.findById(advisorId).orElseThrow(() -> new ResourceNotFoundException("Advisor not found for id:" +advisorId));

        Client client = clientRepository.findById(clientId).orElseThrow(()-> new ResourceNotFoundException("Client not found for id:" +clientId));

        Client c = mapToEntity(clientDto);
        c.setClient_id(client.getClient_id());
        c.setAdvisor(advisor);

        Client savedclient = clientRepository.save(c);
        return mapToDto(savedclient);
    }

    ClientDto mapToDto(Client client){
        ClientDto dto = modelMapper.map(client, ClientDto.class);
        return dto;
    }

    Client mapToEntity(ClientDto clientDto){
       Client client =  modelMapper.map(clientDto, Client.class);
       return client;
    }
}
