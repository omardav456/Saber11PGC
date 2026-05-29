package com.saber11.exam.infraestructure.driver_adapters.jpa_repository;

import com.saber11.exam.domain.model.Area;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class SimulacroData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Area area;
    
}
