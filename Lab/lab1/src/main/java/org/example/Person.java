package org.example;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Person implements Serializable, Comparable<Person> {
    String name;
    String surname;
    String age;
    Set<Person> children;

    Person(String name, String surname, String age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.children = new TreeSet<>();
    }

    public void addChild(Person child) {
        children.add(child);
    }

    public Set<Person> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(age, person.age) &&
                Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age);
    }

    @Override
    public String toString() {
        return name + " " + surname + ", Age: " + age;
    }

    @Override
    public int compareTo(Person other) {
        int result = this.name.compareTo(other.name);
        if (result == 0) {
            result = this.surname.compareTo(other.surname);
            if(result == 0){
                result = this.age.compareTo(other.age);
            }
        }
        return result;
    }
}
