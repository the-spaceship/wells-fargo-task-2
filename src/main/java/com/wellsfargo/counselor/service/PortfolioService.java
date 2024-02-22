package com.wellsfargo.counselor.service;

import com.wellsfargo.counselor.payload.PortfolioDto;

public interface PortfolioService {
    PortfolioDto createPortfolio(PortfolioDto portfolioDto, long clientId);

    void deletePortfolio(long portfolioId);
}
