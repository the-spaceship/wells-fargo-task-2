package com.wellsfargo.counselor.service;

import com.wellsfargo.counselor.payload.AdvisorDto;

import java.util.List;

public interface AdvisorService {
    AdvisorDto createAdvisor(AdvisorDto advisorDto);

    AdvisorDto getAdvisorByadvisor_id(long advisor_id);


    List<AdvisorDto> getAllAdvisors();
}
