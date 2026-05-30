package com.saber11.exam.infraestructure.entry_points;

import com.saber11.exam.domain.model.Categoria;
import com.saber11.exam.domain.model.Simulacro;
import com.saber11.exam.domain.usecase.SimulacroUseCase;
import com.saber11.exam.infraestructure.driver_adapters.jpa_repository.SimulacroData;
import com.saber11.exam.infraestructure.driver_adapters.jpa_repository.SimulacroDataJpaRepository;
import com.saber11.exam.infraestructure.mapper.MapperSimulacro;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/saber11/simulacro")
@RequiredArgsConstructor
public class QuestionContrroller {
    private final SimulacroUseCase simulacroUseCase;
    private final MapperSimulacro mapperSimulacro;

    @PostMapping("/")
    public ResponseEntity<Simulacro> createSimulacro(@RequestBody SimulacroData sumulacroData){
         Simulacro simulacro= simulacroUseCase.createSimulacro(mapperSimulacro.toSimulacro(sumulacroData));
         return new ResponseEntity<>(simulacro, HttpStatus.CREATED);
    }
    @PostConstruct
    public void test() {
        System.out.println("URL_ALL_QUESTIONS = "
                + System.getenv("URL_ALL_QUESTIONS"));
    }

    @PostMapping("/auto/")
    public ResponseEntity<Simulacro> createSimulacroAuto(){
        Simulacro simulacro= simulacroUseCase.createSimulacroAuto();
        return new ResponseEntity<>(simulacro, HttpStatus.CREATED);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Simulacro>> getSimulacroByCategoria(@PathVariable Categoria categoria){
        List<Simulacro> simulacro= simulacroUseCase.getSimulacroByCategoria(categoria);
        return new ResponseEntity<>(simulacro, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteSimulacro(Long id){
        simulacroUseCase.deleteSimulacro(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Simulacro> getSimulacroById(Long id){
        Simulacro simulacro=simulacroUseCase.getSimulacroById(id);
        return new ResponseEntity<>(simulacro, HttpStatus.OK);
    }

    public ResponseEntity<List<Simulacro>> getAllSimulacro(){
        return new ResponseEntity<>(simulacroUseCase.getAllSimulacro(), HttpStatus.OK);
    }
}
