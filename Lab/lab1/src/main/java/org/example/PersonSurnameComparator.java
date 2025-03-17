package org.example;

import java.util.Comparator;


public class PersonSurnameComparator implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        try {
            int age1 = Integer.parseInt(person1.age);
            int age2 = Integer.parseInt(person2.age);
            int result = Integer.compare(age1, age2);

            if (result == 0) {
                result = person1.surname.compareTo(person2.surname);

                if (result == 0) {
                    result = person1.name.compareTo(person2.name);
                }
            }

            return result;
        } catch (NumberFormatException e) {
            int result = person1.age.compareTo(person2.age);

            if (result == 0) {
                result = person1.surname.compareTo(person2.surname);

                if (result == 0) {
                    result = person1.name.compareTo(person2.name);
                }
            }

            return result;
        }
    }
}