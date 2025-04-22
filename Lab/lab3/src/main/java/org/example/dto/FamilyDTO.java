package org.example.dto;

import org.example.entity.Family;

/**
 * DTO класс для Family
 * Используется для передачи данных между слоями приложения
 */
public class FamilyDTO {
    private Long id;
    private String surname;
    private String address;

    // Конструктор по умолчанию
    public FamilyDTO() {}

    // Конструктор с параметрами
    public FamilyDTO(Long id, String surname, String address) {
        this.id = id;
        this.surname = surname;
        this.address = address;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Преобразование сущности Family в DTO
     */
    public static FamilyDTO fromEntity(Family family) {
        return new FamilyDTO(
            family.getId(),
            family.getSurname(),
            family.getAddress()
        );
    }

    /**
     * Преобразование DTO в сущность Family
     */
    public Family toEntity() {
        Family family = new Family();
        family.setId(this.id);
        family.setSurname(this.surname);
        family.setAddress(this.address);
        return family;
    }

    @Override
    public String toString() {
        return String.format("Family(id=%d, surname='%s', address='%s')", 
            id, surname, address);
    }
} 