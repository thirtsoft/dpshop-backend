package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.ScategoryDto;
import com.dp.dpshopbackend.dto.StateDto;

import java.util.List;

public interface StateService {

    StateDto save(StateDto stateDto);

    StateDto update(Long id, StateDto stateDto);

    StateDto findById(Long id);

    List<StateDto> findAll();

    List<StateDto> findByOrderByIdDesc();

    List<StateDto> findAllStateByCountryCode(String code);

    void delete(Long id);

}
