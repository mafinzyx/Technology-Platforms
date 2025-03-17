package org.example;

import java.io.*;
import java.util.*;

public class Database {
    private Set<Person> people;
    private boolean isHashSet;


    public Database() {
        this.people = new TreeSet<>();
        this.isHashSet = false;
    }

    public Database(Set<Person> peopleSet) {
        this.people = peopleSet;
        this.isHashSet = (peopleSet instanceof HashSet);
    }

    public void addPerson(Person person) {
        if (people.add(person)) {
            System.out.println("Person added: " + person.name + " " + person.surname + ", Age: " + person.age);
        } else {
            System.out.println("Person already exists: " + person.name + " " + person.surname + ", Age: " + person.age);
        }
    }

    public void displayPeople() {
        if (people.isEmpty()) {
            System.out.println("Database is empty.");
        } else {
            System.out.println("People in Database (unsorted):");

            if (isHashSet) {
                for (Person person : people) {
                    displayPersonRecursive(person, 0);
                }
            } else {
                List<Person> unsortedList = new ArrayList<>(people);
                Collections.shuffle(unsortedList);
                for (Person person : unsortedList) {
                    displayPersonRecursive(person, 0);
                }
            }
        }
    }

    public void displayPeopleByNaturalOrder() {
        if (people.isEmpty()) {
            System.out.println("Database is empty.");
        } else {
            System.out.println("People in Database (sorted by natural order - name, surname, age):");

            List<Person> sortedPeople = new ArrayList<>(people);
            Collections.sort(sortedPeople);

            for (Person person : sortedPeople) {
                displayPersonRecursive(person, 0);
            }
        }
    }

    public void displayPeopleByAge() {
        if (people.isEmpty()) {
            System.out.println("Database is empty.");
        } else {
            System.out.println("People in Database (sorted by age):");

            List<Person> sortedPeople = new ArrayList<>(people);
            sortedPeople.sort(new PersonSurnameComparator());

            for (Person person : sortedPeople) {
                displayPersonRecursive(person, 0);
            }
        }
    }

    private void displayPersonRecursive(Person person, int level) {
        System.out.println("\t".repeat(level) + "- " + person);
        for (Person child : person.getChildren()) {
            displayPersonRecursive(child, level + 1);
        }
    }

    public void savePeople(Scanner input){
        System.out.print("Enter the filename to save data: ");
        String filename = input.nextLine();
        try (FileWriter writer = new FileWriter(filename, true)) {
            for (Person person : people) {
                writer.write(person.name + " " + person.surname + System.lineSeparator());
            }
            System.out.println("Database saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error while saving to file: " + e.getMessage());
        }
    }

    public void loadPeople(Scanner scanner) {
        System.out.print("Enter the filename to load data: ");
        String filename = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(" ");
                if (parts.length == 3) {
                    Person person = new Person(parts[0], parts[1], parts[2]);
                    addPerson(person);
                }
            }
            System.out.println("Data loaded successfully from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }


    public void getStats(){
        for(Map.Entry<Person, Integer> entry : getInfo().entrySet()){
            System.out.println(entry.getKey().name + ": " + entry.getValue() +  " descendants");
        }
    }

    private Map<Person, Integer> getInfo(){
        Map<Person, Integer> result = new HashMap<>();
        for (Person person : people){
            result.put(person, getAmountOfKids(person));
        }
        return result;
    }

    private Integer getAmountOfKids(Person person){
        int result = 0;
        for (Person child : person.getChildren()){
            result += getAmountOfKids(child) + 1;
        }
        return result;
    }


    public void clearPeople(){
        people.clear();
        System.out.println("Database is clear now.");
    }
}
