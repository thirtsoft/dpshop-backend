package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.StateDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.State;
import com.dp.dpshopbackend.repository.StateRepository;
import com.dp.dpshopbackend.services.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }


    @Override
    public StateDto save(StateDto stateDto) {
        stateDto.setActif(true);
        return StateDto.fromEntityToDto(
                stateRepository.save(
                        StateDto.fromDtoToEntity(stateDto)
                )
        );
    }

    @Override
    public StateDto update(Long id, StateDto stateDto) {
        if (!stateRepository.existsById(id)) {
            throw new ResourceNotFoundException("State not found");
        }
        Optional<State> stateOptional = stateRepository.findById(id);
        if (!stateOptional.isPresent()) {
            throw new ResourceNotFoundException("State not found");
        }
        StateDto stateDtoResult = StateDto.fromEntityToDto(stateOptional.get());
        stateDtoResult.setName(stateDto.getName());
        stateDtoResult.setCountryDto(stateDto.getCountryDto());
        return StateDto.fromEntityToDto(
                stateRepository.save(
                        StateDto.fromDtoToEntity(stateDtoResult)
                )
        );
    }

    @Override
    public StateDto findById(Long id) {
        if (id == null) {
            log.error("Country Id is null");
            return null;
        }
        Optional<State> stateOptional = stateRepository.findById(id);
        return Optional.of(StateDto.fromEntityToDto(stateOptional.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun State avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<StateDto> findAllStateByCountryCode(String code) {
        if (code == null) {
            log.error("State Country is null");
        }
        return stateRepository.findByCountryCode(code).stream()
                .map(StateDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StateDto> findAllActiveStates() {
        return stateRepository.findAll().stream()
                .map(StateDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteState(Long stateId) {
        if (stateId == null) {
            log.error("state Id is null");
            return;
        }
        Optional<State> stateOptional = stateRepository.findById(stateId);
        State state = stateOptional.get();
        state.setActif(false);
        stateRepository.save(state);
    }
}
