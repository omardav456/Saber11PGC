package com.saber11.exam.infraestructure.driver_adapters.jpa_repository;

import com.saber11.exam.domain.model.Area;
import com.saber11.exam.domain.model.Categoria;
import com.saber11.exam.domain.model.Question;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "exam")
@AllArgsConstructor
public class SimulacroData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @Column()
    private Integer tiempoLimite;
    private List<Question> questions;

}
