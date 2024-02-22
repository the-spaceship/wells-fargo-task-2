package com.wellsfargo.counselor.controller;


import com.wellsfargo.counselor.payload.ClientDto;
import com.wellsfargo.counselor.payload.PortfolioDto;
import com.wellsfargo.counselor.service.PortfolioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {

    private PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    //http://localhost:8080/api/portfolios?clientId=1
    @PostMapping
    public ResponseEntity<PortfolioDto> createPortfolio(@RequestBody PortfolioDto portfolioDto, @RequestParam long clientId){
        PortfolioDto dto = portfolioService.createPortfolio(portfolioDto, clientId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/portfolios/1
    @DeleteMapping("/{portfolio_id}")
    public ResponseEntity<String> deletePortfolio(@PathVariable long portfolio_id){
        portfolioService.deletePortfolio(portfolio_id);
        return new ResponseEntity<>("portfolio is deleted!", HttpStatus.OK);
    }
}
