package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.StateApi;
import com.dp.dpshopbackend.dto.StateDto;
import com.dp.dpshopbackend.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class StateController implements StateApi {

    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }


    @Override
    public ResponseEntity<StateDto> save(StateDto stateDto) {
        return ResponseEntity.ok(stateService.save(stateDto));
    }

    @Override
    public ResponseEntity<StateDto> update(Long id, StateDto stateDto) {
        stateDto.setIdState(id);
        return ResponseEntity.ok(stateService.save(stateDto));
    }

    @Override
    public ResponseEntity<StateDto> findById(Long id) {
        return ResponseEntity.ok(stateService.findById(id));
    }

    @Override
    public ResponseEntity<StateDto> findByDesignation(String designation) {
        return null;
    }

    @Override
    public List<StateDto> findAll() {
        return stateService.findAll();
    }

    @Override
    public void delete(Long id) {
        stateService.delete(id);
    }
}
