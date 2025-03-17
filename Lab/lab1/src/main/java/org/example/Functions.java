package org.example;

import java.util.Scanner;

public class Functions {
    public void Choose_Option(Scanner input, Database database) {
        while (true) {
            System.out.print("Choose option to do:\n" +
                    "1. Add new Person\n" +
                    "2. Check Database\n" +
                    "3. Save Database\n" +
                    "4. Load Database\n" +
                    "5. Clear Database\n" +
                    "6. Get Stats\n" +
                    "7. Exit\n" +
                    "Option: ");
            String option = input.nextLine();
            switch (option) {
                case "1":
                    inputFunction(input, database);
                    break;
                case "2":
                    displayDatabaseWithSortingOptions(input, database);
                    break;
                case "3":
                    database.savePeople(input);
                    break;
                case "4":
                    database.loadPeople(input);
                    break;
                case "5":
                    database.clearPeople();
                    break;
                case "6":
                    database.getStats();
                    break;
                case "7":
                    System.out.print("Bye!\n");
                    return;
                default:
                    System.out.print("Input Error, write only allowed number!\n\n");
            }
        }
    }

    private void displayDatabaseWithSortingOptions(Scanner input, Database database) {
        while (true) {
            System.out.print("Choose display option:\n" +
                    "1. Display without sorting\n" +
                    "2. Display with natural order sorting (name, surname, age)\n" +
                    "3. Display with alternative sorting (age, surname, name)\n" +
                    "4. Back to main menu\n" +
                    "Option: ");
            String option = input.nextLine();
            switch (option) {
                case "1":
                    database.displayPeople();
                    return;
                case "2":
                    database.displayPeopleByNaturalOrder();
                    return;
                case "3":
                    database.displayPeopleByAge();
                    return;
                case "4":
                    return;
                default:
                    System.out.print("Input Error, write only allowed number!\n\n");
            }
        }
    }


    private void inputFunction(Scanner input, Database database) {
        System.out.print("Write your name: ");
        String name = input.nextLine().trim();

        System.out.print("Write your surname: ");
        String surname = input.nextLine().trim();

        System.out.print("Write your age: ");
        String age = input.nextLine().trim();

        Person person = new Person(name, surname, age);

        System.out.print("Does this person have children? (yes/no): ");
        String hasChildren = input.nextLine().trim();

        if (hasChildren.equalsIgnoreCase("yes")) {
            while (true) {
                System.out.print("Enter child's name (or 'done' to finish): ");
                String childName = input.nextLine().trim();
                if (childName.equalsIgnoreCase("done")) break;

                System.out.print("Enter child's surname: ");
                String childSurname = input.nextLine().trim();

                System.out.print("Enter child's age: ");
                String childAge = input.nextLine().trim();

                Person child = new Person(childName, childSurname, childAge);
                person.addChild(child);
            }
        }
        database.addPerson(person);
    }
}
