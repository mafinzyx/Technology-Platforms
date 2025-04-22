package org.example.repository;

import org.example.entity.Family;
import java.util.Optional;

/**
 * Интерфейс репозитория для работы с сущностью Family
 * Определяет основные операции CRUD
 */
public interface FamilyRepository {
    /**
     * Сохранение новой семьи
     * @param family семья для сохранения
     * @throws IllegalArgumentException если семья с таким ID уже существует
     */
    void save(Family family);

    /**
     * Поиск семьи по ID
     * @param id ID семьи
     * @return Optional с найденной семьей или пустой Optional
     */
    Optional<Family> findById(Long id);

    /**
     * Удаление семьи по ID
     * @param id ID семьи для удаления
     * @throws IllegalArgumentException если семья с таким ID не существует
     */
    void deleteById(Long id);
} 