package org.example;

import java.util.Scanner;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {
    private static final String NO_SORT = "nosort";
    private static final String NATURAL_SORT = "natural";
    private static final String ALTERNATIVE_SORT = "alternative";

    private static final int TEST_ELEMENTS_COUNT = 5;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String sortType = NO_SORT;
        if (args.length > 0) {
            sortType = args[0].toLowerCase();
        }

        Database database;

        switch (sortType) {
            case NATURAL_SORT:
                System.out.println("Using natural order sorting (TreeSet)");
                database = new Database(new TreeSet<>());
                break;
            case ALTERNATIVE_SORT:
                System.out.println("Using alternative sorting by age (TreeSet with Comparator)");
                database = new Database(new TreeSet<>(new PersonSurnameComparator()));
                break;
            case NO_SORT:
            default:
                System.out.println("Using no sorting (HashSet)");
                database = new Database(new HashSet<>());
                break;
        }

        if (args.length > 1 && args[1].equalsIgnoreCase("test")) {
            createTestData(database);
            System.out.println("Test data created with diverse examples to show different sorting behaviors");

            System.out.println("\n=== DISPLAY WITHOUT SORTING ===");
            database.displayPeople();

            System.out.println("\n=== DISPLAY WITH NATURAL ORDER SORTING (name, surname, age) ===");
            database.displayPeopleByNaturalOrder();

            System.out.println("\n=== DISPLAY WITH ALTERNATIVE SORTING (age, surname, name) ===");
            database.displayPeopleByAge();
        }

        Functions functions = new Functions();
        functions.Choose_Option(input, database);
    }


    private static void createTestData(Database database) {
        database.addPerson(new Person("Adam", "Smith", "45"));
        database.addPerson(new Person("Adam", "Johnson", "30"));
        database.addPerson(new Person("Adam", "Brown", "25"));

        database.addPerson(new Person("John", "Wilson", "50"));
        database.addPerson(new Person("Michael", "Wilson", "35"));
        database.addPerson(new Person("David", "Wilson", "40"));

        database.addPerson(new Person("Emma", "Davis", "28"));
        database.addPerson(new Person("Olivia", "Taylor", "28"));
        database.addPerson(new Person("Sophia", "Miller", "28"));

        database.addPerson(new Person("William", "Jones", "55"));
        database.addPerson(new Person("James", "Garcia", "22"));
        database.addPerson(new Person("Benjamin", "Martinez", "33"));

        Person parent1 = new Person("Robert", "Anderson", "48");
        Person child1 = new Person("Emily", "Anderson", "18");
        Person grandchild1 = new Person("Lily", "Anderson", "2");
        child1.addChild(grandchild1);
        parent1.addChild(child1);
        database.addPerson(parent1);

        Person parent2 = new Person("Jennifer", "Thomas", "42");
        Person child2 = new Person("Ethan", "Thomas", "15");
        Person child3 = new Person("Ava", "Thomas", "12");
        parent2.addChild(child2);
        parent2.addChild(child3);
        database.addPerson(parent2);
    }
}
