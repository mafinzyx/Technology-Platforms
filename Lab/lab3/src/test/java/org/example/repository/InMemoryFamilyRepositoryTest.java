package org.example.repository;

import org.example.entity.Family;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for InMemoryFamilyRepository
 */
class InMemoryFamilyRepositoryTest {
    private InMemoryFamilyRepository repository;
    private Family testFamily;

    @BeforeEach
    void setUp() {
        System.out.println("\n=== Setting up test environment ===");
        repository = new InMemoryFamilyRepository();
        testFamily = new Family();
        testFamily.setId(1L);
        testFamily.setSurname("Test");
        testFamily.setAddress("Test Address");
        System.out.println("Test environment is ready");
    }

    @Test
    void save_ShouldSaveNewFamily() {
        System.out.println("\n=== Test: save_ShouldSaveNewFamily ===");
        System.out.println("Attempting to save a new family...");

        // When
        repository.save(testFamily);
        System.out.println("Family successfully saved");

        // Then
        System.out.println("Verifying saved family...");
        assertThat(repository.findById(1L))
                .isPresent()
                .hasValueSatisfying(family -> {
                    assertThat(family.getId()).isEqualTo(1L);
                    assertThat(family.getSurname()).isEqualTo("Test");
                    assertThat(family.getAddress()).isEqualTo("Test Address");
                });
        System.out.println("Test completed successfully: family saved and verified");
    }

    @Test
    void save_ShouldThrowException_WhenFamilyWithSameIdExists() {
        System.out.println("\n=== Test: save_ShouldThrowException_WhenFamilyWithSameIdExists ===");
        System.out.println("Preparing: saving test family...");

        // Given
        repository.save(testFamily);
        System.out.println("Test family saved");

        // When & Then
        System.out.println("Attempting to save family with same ID...");
        assertThrows(IllegalArgumentException.class, () -> repository.save(testFamily));
        System.out.println("Test completed successfully: expected exception received");
    }

    @Test
    void findById_ShouldReturnEmpty_WhenFamilyDoesNotExist() {
        System.out.println("\n=== Test: findById_ShouldReturnEmpty_WhenFamilyDoesNotExist ===");
        System.out.println("Attempting to find non-existent family...");

        // When
        var result = repository.findById(999L);
        System.out.println("Search result received");

        // Then
        System.out.println("Verifying result...");
        assertThat(result).isEmpty();
        System.out.println("Test completed successfully: empty result as expected");
    }

    @Test
    void findById_ShouldReturnFamily_WhenFamilyExists() {
        System.out.println("\n=== Test: findById_ShouldReturnFamily_WhenFamilyExists ===");
        System.out.println("Preparing: saving test family...");

        // Given
        repository.save(testFamily);
        System.out.println("Test family saved");

        // When
        System.out.println("Searching for saved family...");
        var result = repository.findById(1L);
        System.out.println("Search result received");

        // Then
        System.out.println("Verifying result...");
        assertThat(result)
                .isPresent()
                .hasValue(testFamily);
        System.out.println("Test completed successfully: correct family found");
    }

    @Test
    void deleteById_ShouldDeleteFamily_WhenFamilyExists() {
        System.out.println("\n=== Test: deleteById_ShouldDeleteFamily_WhenFamilyExists ===");
        System.out.println("Preparing: saving test family...");

        // Given
        repository.save(testFamily);
        System.out.println("Test family saved");

        // When
        System.out.println("Deleting family...");
        repository.deleteById(1L);
        System.out.println("Family deleted");

        // Then
        System.out.println("Verifying deletion result...");
        assertThat(repository.findById(1L)).isEmpty();
        System.out.println("Test completed successfully: family successfully deleted");
    }

    @Test
    void deleteById_ShouldThrowException_WhenFamilyDoesNotExist() {
        System.out.println("\n=== Test: deleteById_ShouldThrowException_WhenFamilyDoesNotExist ===");
        System.out.println("Attempting to delete non-existent family...");

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> repository.deleteById(999L));
        System.out.println("Test completed successfully: expected exception received");
    }
}