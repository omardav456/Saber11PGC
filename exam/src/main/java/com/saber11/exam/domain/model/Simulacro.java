package com.saber11.exam.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Simulacro {
    private Long id;
    private Categoria categoria;
    private Integer tiempoLimite;
    private List<Long> questionIds;

}
