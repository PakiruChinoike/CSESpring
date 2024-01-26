package com.io.github.pakiruchinoike.service;

import com.io.github.pakiruchinoike.rest.dto.CombatLogDTO;

public interface CombatLogService {
    
    CombatLogDTO save(CombatLogDTO dto);
    CombatLogDTO findLatest(Integer id);
    CombatLogDTO update(CombatLogDTO dto, Integer id);
    void delete(Integer id);
}
