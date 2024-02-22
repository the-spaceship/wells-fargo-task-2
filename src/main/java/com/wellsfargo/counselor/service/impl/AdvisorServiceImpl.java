package com.wellsfargo.counselor.service.impl;

import com.wellsfargo.counselor.entity.Advisor;
import com.wellsfargo.counselor.exception.ResourceNotFoundException;
import com.wellsfargo.counselor.payload.AdvisorDto;
import com.wellsfargo.counselor.repository.AdvisorRepository;
import com.wellsfargo.counselor.service.AdvisorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvisorServiceImpl implements AdvisorService {

    private AdvisorRepository advisorRepository;
    private ModelMapper modelMapper;

    public AdvisorServiceImpl(AdvisorRepository advisorRepository, ModelMapper modelMapper) {

        this.advisorRepository = advisorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AdvisorDto createAdvisor(AdvisorDto advisorDto) {
       Advisor advisor =  mapToEntity(advisorDto);
        Advisor savedAdvisor = advisorRepository.save(advisor);

     AdvisorDto dto = mapToDto(savedAdvisor);
        return dto;
    }

    @Override
    public AdvisorDto getAdvisorByadvisor_id(long advisor_id) {
        Advisor advisor = advisorRepository.findById(advisor_id).orElseThrow(() ->  new ResourceNotFoundException("Post not found with id:" + advisor_id));
        AdvisorDto dto = new AdvisorDto();
        dto.setAdvisor_id(advisor.getAdvisor_id());
        dto.setFirst_name(advisor.getFirst_name());
        dto.setLast_name(advisor.getLast_name());
        dto.setEmail(advisor.getEmail());
        dto.setPhone(advisor.getPhone());
        return dto;
    }

    @Override
    public List<AdvisorDto> getAllAdvisors() {
        List<Advisor> advisors = advisorRepository.findAll();
        List<AdvisorDto> dtos = advisors.stream().map(a -> mapToDto(a)).collect(Collectors.toList());
        return dtos;
    }



    AdvisorDto mapToDto(Advisor advisor){
       AdvisorDto dto = modelMapper.map(advisor, AdvisorDto.class);
       return dto;
   }

   Advisor mapToEntity(AdvisorDto advisorDto){
       Advisor post = modelMapper.map(advisorDto, Advisor.class);
       return post;
   }
}
