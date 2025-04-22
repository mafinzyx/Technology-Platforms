package org.example;

import org.example.entity.Family;
import org.example.entity.Person;
import org.example.service.FamilyService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final FamilyService familyService = new FamilyService();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        // Add some test data
        addTestData();

        while (true) {
            System.out.println("\nFamily Management System");
            System.out.println("1. Add new family");
            System.out.println("2. Add new person to family");
            System.out.println("3. Remove person");
            System.out.println("4. Remove family");
            System.out.println("5. Show all families");
            System.out.println("6. Show all persons");
            System.out.println("7. Show persons older than age");
            System.out.println("8. Show families with more than N members");
            System.out.println("9. Show persons by occupation");
            System.out.println("10. Show largest family");
            System.out.println("11. Show top N persons (by id)");
            System.out.println("12. Show N youngest persons (by age)");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addNewFamily();
                    break;
                case 2:
                    addNewPerson();
                    break;
                case 3:
                    removePerson();
                    break;
                case 4:
                    removeFamily();
                    break;
                case 5:
                    showAllFamilies();
                    break;
                case 6:
                    showAllPersons();
                    break;
                case 7:
                    showPersonsOlderThan();
                    break;
                case 8: showFamiliesWithMoreThanMembers();
                    break;
                case 9: showPersonsByOccupation();
                    break;
                case 10:
                    showLargestFamily();
                    break;
                case 11:
                    showTopNPersons();
                    break;
                case 12:
                    showNYoungestPersons();
                    break;
                case 0: {
                    familyService.close();
                    System.exit(0);
                }
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void addTestData() {
        // Create test families
        Family family1 = familyService.addFamily("Smith", "123 Main St");
        Family family2 = familyService.addFamily("Johnson", "456 Oak Ave");

        // Add test persons to family1
        familyService.addPerson("John", "Smith", LocalDate.of(1980, 5, 15), "Engineer", family1);
        familyService.addPerson("Mary", "Smith", LocalDate.of(1982, 8, 20), "Teacher", family1);
        familyService.addPerson("James", "Smith", LocalDate.of(2010, 3, 10), "Student", family1);

        // Add test persons to family2
        familyService.addPerson("Robert", "Johnson", LocalDate.of(1975, 11, 30), "Doctor", family2);
        familyService.addPerson("Sarah", "Johnson", LocalDate.of(1978, 4, 25), "Lawyer", family2);
    }

    private static void addNewFamily() {
        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        Family family = familyService.addFamily(surname, address);
        System.out.println("Family added successfully: " + family);
    }

    private static void addNewPerson() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter birth date (yyyy-MM-dd): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine(), dateFormatter);
        System.out.print("Enter occupation: ");
        String occupation = scanner.nextLine();

        List<Family> families = familyService.getAllFamilies();
        if (families.isEmpty()) {
            System.out.println("No families available. Please add a family first.");
            return;
        }

        System.out.println("Available families:");
        for (int i = 0; i < families.size(); i++) {
            System.out.println((i + 1) + ". " + families.get(i));
        }
        System.out.print("Choose family number: ");
        int familyIndex = scanner.nextInt() - 1;

        if (familyIndex >= 0 && familyIndex < families.size()) {
            Family family = families.get(familyIndex);
            Person person = familyService.addPerson(firstName, lastName, birthDate, occupation, family);
            System.out.println("Person added successfully: " + person);
        } else {
            System.out.println("Invalid family number!");
        }
    }

    private static void removePerson() {
        List<Person> persons = familyService.getAllPersons();
        if (persons.isEmpty()) {
            System.out.println("No persons available.");
            return;
        }

        System.out.println("Available persons:");
        for (int i = 0; i < persons.size(); i++) {
            System.out.println((i + 1) + ". " + persons.get(i));
        }
        System.out.print("Choose person number to remove: ");
        int personIndex = scanner.nextInt() - 1;

        if (personIndex >= 0 && personIndex < persons.size()) {
            Person person = persons.get(personIndex);
            familyService.removePerson(person);
            System.out.println("Person removed successfully.");
        } else {
            System.out.println("Invalid person number!");
        }
    }

    private static void showNYoungestPersons() {
        System.out.print("Enter the amount of records: ");
        int topN = scanner.nextInt();
        List<Person> persons = familyService.getNYoungest(topN);
        if (persons.isEmpty()) {
            System.out.println("No persons found.");
            return;
        } else{
            for (Person person : persons) {
                System.out.println(person);
            }
        }
    }

    private static void showTopNPersons(){
        System.out.print("Enter the amount of records: ");
        int topN = scanner.nextInt();
        List<Person> persons = familyService.getTopNPersons(topN);
        if (persons.isEmpty()) {
            System.out.println("No persons found.");
            return;
        } else{
            for (Person person : persons) {
                System.out.println(person);
            }
        }
    }


    private static void removeFamily() {
        List<Family> families = familyService.getAllFamilies();
        if (families.isEmpty()) {
            System.out.println("No families available.");
            return;
        }

        System.out.println("Available families:");
        for (int i = 0; i < families.size(); i++) {
            System.out.println((i + 1) + ". " + families.get(i));
        }
        System.out.print("Choose family number to remove: ");
        int familyIndex = scanner.nextInt() - 1;

        if (familyIndex >= 0 && familyIndex < families.size()) {
            Family family = families.get(familyIndex);
            familyService.removeFamily(family);
            System.out.println("Family removed successfully.");
        } else {
            System.out.println("Invalid family number!");
        }
    }

    private static void showAllFamilies() {
        List<Family> families = familyService.getAllFamilies();
        if (families.isEmpty()) {
            System.out.println("No families available.");
            return;
        }

        System.out.println("All families:");
        for (Family family : families) {
            System.out.println(family);
            System.out.println("Members:");
            for (Person person : family.getMembers()) {
                System.out.println("  - " + person);
            }
        }
    }

    private static void showAllPersons() {
        List<Person> persons = familyService.getAllPersons();
        if (persons.isEmpty()) {
            System.out.println("No persons available.");
            return;
        }

        System.out.println("All persons:");
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    private static void showPersonsOlderThan() {
        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        List<Person> persons = familyService.getPersonsOlderThan(age);
        if (persons.isEmpty()) {
            System.out.println("No persons found older than " + age + " years.");
            return;
        }

        System.out.println("Persons older than " + age + " years:");
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    private static void showFamiliesWithMoreThanMembers() {
        System.out.print("Enter minimum number of members: ");
        int count = scanner.nextInt();

        List<Family> families = familyService.getFamiliesWithMoreThanMembers(count);
        if (families.isEmpty()) {
            System.out.println("No families found with more than " + count + " members.");
            return;
        }

        System.out.println("Families with more than " + count + " members:");
        for (Family family : families) {
            System.out.println(family);
        }
    }

    private static void showPersonsByOccupation() {
        System.out.print("Enter occupation: ");
        String occupation = scanner.nextLine();

        List<Person> persons = familyService.getPersonsByOccupation(occupation);
        if (persons.isEmpty()) {
            System.out.println("No persons found with occupation: " + occupation);
            return;
        }

        System.out.println("Persons with occupation '" + occupation + "':");
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    private static void showLargestFamily() {
        try {
            Family family = familyService.getLargestFamily();
            System.out.println("Largest family:");
            System.out.println(family);
            System.out.println("Members:");
            for (Person person : family.getMembers()) {
                System.out.println("  - " + person);
            }
        } catch (Exception e) {
            System.out.println("No families available.");
        }
    }
}