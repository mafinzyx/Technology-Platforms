package org.example.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "families")
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Person> members = new ArrayList<>();

    // Constructors
    public Family() {}

    public Family(String surname, String address) {
        this.surname = surname;
        this.address = address;
    }

    // Getters and Setters
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

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }

    // Helper method to add a person to the family
    public void addMember(Person person) {
        members.add(person);
        person.setFamily(this);
    }

    // Helper method to remove a person from the family
    public void removeMember(Person person) {
        members.remove(person);
        person.setFamily(null);
    }

    @Override
    public String toString() {
        return "Family{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", membersCount=" + members.size() +
                '}';
    }
} 