package org.example.controller;

import org.example.dto.FamilyDTO;
import org.example.entity.Family;
import org.example.repository.FamilyRepository;

/**
 * Контроллер для работы с Family
 * Обрабатывает запросы и возвращает строковые ответы
 */
public class FamilyController {
    private final FamilyRepository repository;

    public FamilyController(FamilyRepository repository) {
        this.repository = repository;
    }

    /**
     * Сохранение новой семьи
     * @param familyDTO данные семьи
     * @return "done" при успешном сохранении, "bad request" при ошибке
     */
    public String save(FamilyDTO familyDTO) {
        try {
            Family family = familyDTO.toEntity();
            repository.save(family);
            return "done";
        } catch (IllegalArgumentException e) {
            return "bad request";
        }
    }

    /**
     * Поиск семьи по ID
     * @param id ID семьи
     * @return строковое представление семьи или "not found"
     */
    public String findById(Long id) {
        return repository.findById(id)
                .map(FamilyDTO::fromEntity)
                .map(FamilyDTO::toString)
                .orElse("not found");
    }

    /**
     * Удаление семьи по ID
     * @param id ID семьи
     * @return "done" при успешном удалении, "not found" если семья не найдена
     */
    public String deleteById(Long id) {
        try {
            repository.deleteById(id);
            return "done";
        } catch (IllegalArgumentException e) {
            return "not found";
        }
    }
} 