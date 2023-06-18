package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.FournisseurDto;
import com.dp.dpshopbackend.dto.HistoriqueLoginDto;

import java.math.BigDecimal;
import java.util.List;

public interface HistoriqueLoginService {

    HistoriqueLoginDto saveHistoriqueLogin(HistoriqueLoginDto historiqueLoginDto);

    HistoriqueLoginDto saveHistoriqueLoginWithConnectedUser(HistoriqueLoginDto historiqueLoginDto, Long userId);

    HistoriqueLoginDto findHistoriqueLoginById(Long id);

    List<HistoriqueLoginDto> findAllHistoriqueLogins();

    List<HistoriqueLoginDto> findAllHistoriqueLoginsOrderDesc();

    BigDecimal countNumberOfHistoriqueLogins();

    void delete(Long id);

    List<HistoriqueLoginDto> findAllActiveHistoriqueLogins();

    void deleteHistoriqueLogin(Long historiqueLoginId);

}
