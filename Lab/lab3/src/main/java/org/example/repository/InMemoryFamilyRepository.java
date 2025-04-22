package org.example.repository;

import org.example.entity.Family;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Реализация репозитория Family с хранением данных в памяти
 * Использует ConcurrentHashMap для потокобезопасности
 */
public class InMemoryFamilyRepository implements FamilyRepository {
    private final Map<Long, Family> families = new ConcurrentHashMap<>();

    @Override
    public void save(Family family) {
        if (family.getId() != null && families.containsKey(family.getId())) {
            throw new IllegalArgumentException("Family with ID " + family.getId() + " already exists");
        }
        families.put(family.getId(), family);
    }

    @Override
    public Optional<Family> findById(Long id) {
        return Optional.ofNullable(families.get(id));
    }

    @Override
    public void deleteById(Long id) {
        if (!families.containsKey(id)) {
            throw new IllegalArgumentException("Family with ID " + id + " not found");
        }
        families.remove(id);
    }
} 