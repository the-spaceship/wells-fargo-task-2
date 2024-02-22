package com.wellsfargo.counselor.service.impl;

import com.wellsfargo.counselor.entity.Advisor;
import com.wellsfargo.counselor.entity.Client;
import com.wellsfargo.counselor.entity.Portfolio;
import com.wellsfargo.counselor.exception.ResourceNotFoundException;
import com.wellsfargo.counselor.payload.ClientDto;
import com.wellsfargo.counselor.payload.PortfolioDto;
import com.wellsfargo.counselor.repository.ClientRepository;
import com.wellsfargo.counselor.repository.PortfolioRepository;
import com.wellsfargo.counselor.service.PortfolioService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImpl implements PortfolioService {
 private ClientRepository clientRepository;
 private PortfolioRepository portfolioRepository;
 private ModelMapper modelMapper;


    public PortfolioServiceImpl(ClientRepository clientRepository, PortfolioRepository portfolioRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.portfolioRepository = portfolioRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PortfolioDto createPortfolio(PortfolioDto portfolioDto, long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("client not found with id:" + clientId));
        Portfolio portfolio = new Portfolio();

        portfolio.setCreationDate(portfolioDto.getCreationDate());

        portfolio.setClient(client);

        Portfolio savedPortfolio = portfolioRepository.save(portfolio);

        PortfolioDto dto = new PortfolioDto();
        dto.setCreationDate(savedPortfolio.getCreationDate());
        return dto;
    }

    @Override
    public void deletePortfolio(long portfolioId) {
        portfolioRepository.deleteById(portfolioId);
    }
}
