package com.wellsfargo.counselor.controller;


import com.wellsfargo.counselor.entity.Portfolio;
import com.wellsfargo.counselor.entity.Security;
import com.wellsfargo.counselor.repository.PortfolioRepository;
import com.wellsfargo.counselor.repository.SecurityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/securities")
public class SecurityController {
    private SecurityRepository securityRepository;
    private PortfolioRepository portfolioRepository;

    public SecurityController(PortfolioRepository portfolioRepository, SecurityRepository securityRepository) {
        this.portfolioRepository = portfolioRepository;
        this.securityRepository = securityRepository;
    }

    @PostMapping("/{portfolioId}")
    public ResponseEntity<Security> addSecurityToPortfolio(@PathVariable Long portfolioId, @RequestBody Security security) {
        Optional<Portfolio> portfolioOptional = portfolioRepository.findById(portfolioId);
        if (portfolioOptional.isPresent()) {
            security.setPortfolio(portfolioOptional.get());
            Security savedSecurity = securityRepository.save(security);
            return ResponseEntity.ok(savedSecurity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{securityId}")
    public ResponseEntity<Security> updateSecurity(@PathVariable Long securityId, @RequestBody Security updatedSecurity) {
        Optional<Security> securityOptional = securityRepository.findById(securityId);
        if (securityOptional.isPresent()) {
            Security security = securityOptional.get();
            security.setName(updatedSecurity.getName());
            security.setCategory(updatedSecurity.getCategory());
            security.setPurchaseDate(updatedSecurity.getPurchaseDate());
            security.setPurchasePrice(updatedSecurity.getPurchasePrice());
            security.setQuantity(updatedSecurity.getQuantity());
            Security savedSecurity = securityRepository.save(security);
            return ResponseEntity.ok(savedSecurity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{securityId}")
    public ResponseEntity<Void> removeSecurity(@PathVariable Long securityId) {
        Optional<Security> securityOptional = securityRepository.findById(securityId);
        if (securityOptional.isPresent()) {
            securityRepository.deleteById(securityId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
