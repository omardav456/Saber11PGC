package com.saber11.questions.infraestructure.driver_adapters.jpa_repository;

import com.saber11.questions.domain.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionDataJpaRepository extends JpaRepository<QuestionData,Long> {

    Optional<List<QuestionData>> findByArea(Area area);

}
