package com.wellsfargo.counselor.controller;

import com.wellsfargo.counselor.payload.AdvisorDto;
import com.wellsfargo.counselor.service.AdvisorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advisors")
public class AdvisorController {
    private AdvisorService advisorService;

    public AdvisorController(AdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    //http://localhost:8080/api/advisors
    @PostMapping
    public ResponseEntity<AdvisorDto> createAdvisor(@RequestBody AdvisorDto advisorDto){
        AdvisorDto dto = advisorService.createAdvisor(advisorDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/advisors/particular?id=1
    @GetMapping("/particular")
    public ResponseEntity<AdvisorDto> getAdvisorByadvisor_id(@RequestParam long advisor_id){
        AdvisorDto dto = advisorService.getAdvisorByadvisor_id(advisor_id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public List<AdvisorDto> getAllAdvisors(){
     List<AdvisorDto> advisorDtos = advisorService.getAllAdvisors();
     return advisorDtos;
    }
}

