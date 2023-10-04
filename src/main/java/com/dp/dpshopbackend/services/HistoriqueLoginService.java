package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.HistoriqueLoginDto;

import java.util.List;

public interface HistoriqueLoginService {

    HistoriqueLoginDto saveHistoriqueLogin(HistoriqueLoginDto historiqueLoginDto);
    
    List<HistoriqueLoginDto> findAllActiveHistoriqueLogins();

    void deleteHistoriqueLogin(Long historiqueLoginId);

}
