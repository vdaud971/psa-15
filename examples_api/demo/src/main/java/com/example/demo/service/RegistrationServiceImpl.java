package com.example.demo.service;

import com.example.demo.entity.Registration;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.payload.RegistrationDto;
import com.example.demo.repository.RegistrationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public RegistrationDto addRegistration(RegistrationDto dto) {
        Registration registration = mapToEntity(dto);
        Registration entity = registrationRepository.save(registration);
        RegistrationDto registrationDto = mapToDto(entity);
        return registrationDto;
    }

    @Override
    public void deleteById(long id) {
        registrationRepository.deleteById(id);
    }

    @Override
    public RegistrationDto updateRegistration(long id, RegistrationDto registrationDto) {
        Optional<Registration> byId = registrationRepository.findById(id);
        Registration registration = byId.get();
        registration.setName(registrationDto.getName());
        registration.setEmail(registrationDto.getEmail());
        registration.setMobile(registration.getMobile());
        Registration save = registrationRepository.save(registration);
        RegistrationDto registrationDto1 = mapToDto(save);
        return registrationDto1;
    }

    @Override
    public List<RegistrationDto> listOfregistration(int pageNo, int pageSize, String sort, String sortDir) {

        Sort sortt = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(Sort.Direction.ASC) : Sort.by(Sort.Direction.DESC);
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sortt);
        Page<Registration> reg = registrationRepository.findAll(pageRequest);
        List<RegistrationDto> collect = reg.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public RegistrationDto findbyId(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(() -> new ResourceNotFound("result not found for id" + id));
        //Registration registration = byId.get();
        RegistrationDto registrationDto = mapToDto(registration);
        return registrationDto;
    }

    private RegistrationDto mapToDto(Registration entity) {
        RegistrationDto dto = new RegistrationDto();
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setMobile(entity.getMobile());
        return dto;
    }

    private Registration mapToEntity(RegistrationDto dto) {
        Registration entity = new Registration();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setMobile(dto.getMobile());
        return entity;
    }

}
