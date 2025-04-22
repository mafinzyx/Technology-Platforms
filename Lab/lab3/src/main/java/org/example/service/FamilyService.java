package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.example.entity.Family;
import org.example.entity.Person;

import java.time.LocalDate;
import java.util.List;

public class FamilyService {
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public FamilyService() {
        this.emf = Persistence.createEntityManagerFactory("familyPU");
        this.em = emf.createEntityManager();
    }

    // Add a new family
    public Family addFamily(String surname, String address) {
        Family family = new Family(surname, address);
        em.getTransaction().begin();
        em.persist(family);
        em.getTransaction().commit();
        return family;
    }

    // Add a new person to a family
    public Person addPerson(String firstName, String lastName, LocalDate birthDate, String occupation, Family family) {
        Person person = new Person(firstName, lastName, birthDate, occupation);
        em.getTransaction().begin();
        family.addMember(person);
        em.persist(person);
        em.getTransaction().commit();
        return person;
    }

    // Remove a person
    public void removePerson(Person person) {
        em.getTransaction().begin();
        Family family = person.getFamily();
        if (family != null) {
            family.removeMember(person);
            em.remove(person);
        }

        if(family != null && family.getMembers().isEmpty()){
            em.remove(family);
        }

        em.getTransaction().commit();
    }

    // Remove a family and all its members
    public void removeFamily(Family family) {
        em.getTransaction().begin();
        em.remove(family);
        em.getTransaction().commit();
    }

    public List<Family> getFamilyByName(String familyName) {
        TypedQuery<Family> query = em.createQuery(
                "SELECT f FROM Family f WHERE f.surname = :family_name",
                Family.class
        );
        query.setParameter("family_name", familyName);
        return query.getResultList();
    }


    // Get all families
    public List<Family> getAllFamilies() {
        TypedQuery<Family> query = em.createQuery("SELECT f FROM Family f", Family.class);
        return query.getResultList();
    }

    public List<Person> getNYoungest(int topN){
        TypedQuery<Person> query = em.createQuery(
                "SELECT p FROM Person p ORDER BY p.birthDate DESC LIMIT :TOP_N",
                Person.class
        );
        query.setParameter("TOP_N", topN);
        return query.getResultList();
    }

    public List<Person> getTopNPersons(int topN) {
        TypedQuery<Person> query = em.createQuery(
                "SELECT p FROM Person p LIMIT :TOP_N",
                Person.class
        );
        query.setParameter("TOP_N", topN);
        return query.getResultList();
    }

        // Get all persons
    public List<Person> getAllPersons() {
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        return query.getResultList();
    }

    // Get persons older than specified age
    public List<Person> getPersonsOlderThan(int age) {
        LocalDate cutoffDate = LocalDate.now().minusYears(age);
        TypedQuery<Person> query = em.createQuery(
            "SELECT p FROM Person p WHERE p.birthDate < :cutoffDate ORDER BY p.birthDate",
            Person.class
        );
        query.setParameter("cutoffDate", cutoffDate);
        return query.getResultList();
    }

    // Get families with more than specified number of members
    public List<Family> getFamiliesWithMoreThanMembers(int count) {
        TypedQuery<Family> query = em.createQuery(
            "SELECT f FROM Family f WHERE SIZE(f.members) > :count",
            Family.class
        );
        query.setParameter("count", count);
        return query.getResultList();
    }

    // Get persons by occupation
    public List<Person> getPersonsByOccupation(String occupation) {
        TypedQuery<Person> query = em.createQuery(
            "SELECT p FROM Person p WHERE p.occupation = :occupation",
            Person.class
        );
        query.setParameter("occupation", occupation);
        return query.getResultList();
    }

    // Get the largest family
    public Family getLargestFamily() {
        TypedQuery<Family> query = em.createQuery(
            "SELECT f FROM Family f ORDER BY SIZE(f.members) DESC",
            Family.class
        );
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public void close() {
        em.close();
        emf.close();
    }
} 